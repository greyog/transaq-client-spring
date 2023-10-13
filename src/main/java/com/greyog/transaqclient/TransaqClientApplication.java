package com.greyog.transaqclient;

import com.greyog.transaqclient.service.ConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Slf4j
public class TransaqClientApplication {

	// sudo docker run -p 50051:50051 kmlebedev/txmlconnector server

	public static void main(String[] args) {
		SpringApplication.run(TransaqClientApplication.class, args);
	}

	@Autowired
	ConnectService connectService;

	@PreDestroy
	public void onExit(){
		log.info("########### Disconnect and exit #############");
		String result = connectService.getDisconnectResult();
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			log.error("",e);
		}
		log.info("Disconnect result: {}", result);
	}
}
