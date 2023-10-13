package com.greyog.transaqclient;

import com.greyog.transaqclient.component.MessageProcessor;
import com.greyog.transaqclient.entity.command.ConnectCommand;
import com.greyog.transaqclient.entity.command.DisconnectCommand;
import com.greyog.transaqclient.entity.command.GetFortsPositionCommand;
import com.greyog.transaqclient.entity.command.ServerStatusCommand;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import transaqConnector.ConnectServiceGrpc;

import javax.xml.bind.Marshaller;
import java.util.HashMap;

@Configuration
public class MyConfigClass {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.greyog.transaqclient.entity.command");
//        marshaller.setClassesToBeBound(
//                ConnectCommand.class,
//                DisconnectCommand.class,
//                ServerStatusCommand.class,
//                GetFortsPositionCommand.class
//        );
        marshaller.setMarshallerProperties(new HashMap<String, Object>() {{
            put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            put(Marshaller.JAXB_FRAGMENT, true);
        }});
        return marshaller;
    }

//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }

//    @Autowired
//    private MessageProcessor messageProcessor;

//    @Bean
//    public CommandLineRunner messageProcessorRunner(TaskExecutor taskExecutor) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                taskExecutor.execute(messageProcessor);
//            }
//        };
//    }
//
//    @GrpcClient("ConnectService")
//    private ConnectServiceGrpc.ConnectServiceBlockingStub blockingStub;
//
//    @Bean
//    public ConnectServiceGrpc.ConnectServiceBlockingStub blockingStub() {
//        return this.blockingStub;
//    }

}
