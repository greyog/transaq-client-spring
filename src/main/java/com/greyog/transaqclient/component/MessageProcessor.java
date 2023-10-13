package com.greyog.transaqclient.component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transaqConnector.Connect;
import transaqConnector.ConnectServiceGrpc;

import java.util.Iterator;

@Slf4j
@Component
public class MessageProcessor implements Runnable {

    @GrpcClient("ConnectService")
    private ConnectServiceGrpc.ConnectServiceBlockingStub blockingStub;

    @SneakyThrows
    @Override
    public void run() {
        Connect.DataRequest dataRequest = Connect.DataRequest.newBuilder()
                .build();
        Iterator<Connect.DataResponse> dataResponseIterator = blockingStub.fetchResponseData(dataRequest);
//        while (true) {
            dataResponseIterator.forEachRemaining(dataResponse ->
                    log.info("Got some data: {}", dataResponse.getMessage())
            );
//            Thread.sleep(1000);
//        }

//        for (int i = 0; dataResponseIterator.hasNext(); i++) {
//            Connect.DataResponse dataResponse = dataResponseIterator.next();
//            log.info("Got some data: {}", dataResponse.getMessage());
//        }
    }
}
