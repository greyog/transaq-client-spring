package com.greyog.transaqclient.service;

import com.greyog.transaqclient.component.MessageProcessor;
import com.greyog.transaqclient.entity.command.ConnectCommand;
import com.greyog.transaqclient.entity.command.DisconnectCommand;
import com.greyog.transaqclient.entity.command.ServerStatusCommand;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import transaqConnector.Connect;
import transaqConnector.ConnectServiceGrpc;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Slf4j
@Service
public class ConnectService {

    @GrpcClient("ConnectService")
    private ConnectServiceGrpc.ConnectServiceBlockingStub blockingStub;

    @Autowired
    private Jaxb2Marshaller marshaller;

    public String getLoginCommand() {
        ConnectCommand connectCommand = new ConnectCommand();
        connectCommand.login = "login_here";
        connectCommand.password = "password_here";
        connectCommand.host = "tr1.finam.ru";
        connectCommand.port = 3900;
        connectCommand.rqdelay = 100;
        connectCommand.session_timeout = 1000;
        connectCommand.request_timeout = 1000;

        return getXMLCommand(connectCommand);
    }

    private String getXMLCommand(Object command) {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        marshaller.marshal(command, result);
        log.info("marshall result: {}", sw);
        return sw.toString();
    }

    public String getLoginResult() {
        String command = getLoginCommand();
        String requestResult = getRequestResult(command);
        return requestResult;
    }

    private String getRequestResult(String command) {
        Connect.SendCommandRequest request = Connect.SendCommandRequest.newBuilder()
                .setMessage(command)
                .build();
        Connect.SendCommandResponse response = blockingStub.sendCommand(request);
        String r = new String(response.toByteArray(), StandardCharsets.UTF_8);
        log.info("", response);
        return r;
    }

    public String getDisconnectCommand() {
        return getXMLCommand(new DisconnectCommand());
    }

    public String getDisconnectResult() {
        return getRequestResult(getDisconnectCommand());
    }

    public String getServerStatus() {
        return getRequestResult(getServerStatusCommand());
    }

    private String getServerStatusCommand() {
        return getXMLCommand(new ServerStatusCommand());
    }

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private MessageProcessor messageProcessor;

    public String initDataFetch() throws Exception {
        taskExecutor.execute(messageProcessor);
        return "Started processing of messages...";
    }
}
