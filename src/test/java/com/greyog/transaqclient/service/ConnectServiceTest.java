package com.greyog.transaqclient.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.HtmlUtils;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class ConnectServiceTest {

    @Autowired
    ConnectService connectService;

    @Test
    void getLoginCommand() {
        connectService.getLoginCommand();
    }

    @Test
    void getLoginResult() {
        String result = connectService.getLoginResult();
        log.info("Login result: {}", result);
    }

    @Test
    void getServerStatus() throws InterruptedException {
        getLoginResult();
        String result = connectService.getServerStatus();
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        String escapedResult = HtmlUtils.htmlEscape(result);
        log.info("Escaped server status: {}", escapedResult);
    }
}