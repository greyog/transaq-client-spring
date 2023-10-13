package com.greyog.transaqclient.controller;

import com.greyog.transaqclient.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class IndexController {

    @Autowired
    private ConnectService connectService;


    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @RequestMapping(params = "login")
    public String getLoginResult(Model model) {
        String loginResult = connectService.getLoginResult();
        model.addAttribute("result", HtmlUtils.htmlEscape(loginResult));
        return "index";
    }

    @RequestMapping(params = "disconnect")
    public String getDisconnectResult(Model model) {
        String disconnectResult = connectService.getDisconnectResult();
        model.addAttribute("result", HtmlUtils.htmlEscape(disconnectResult));
        return "index";
    }

    @RequestMapping(params = "server_status")
    public String getServerStatus(Model model) {
        String serverStatus = connectService.getServerStatus();
        model.addAttribute("result", HtmlUtils.htmlEscape(serverStatus));
        return "index";
    }

    @RequestMapping(params = "fetch_data")
    public String fetchData(Model model) throws Exception {
        String result = connectService.initDataFetch();
        model.addAttribute("result", HtmlUtils.htmlEscape(result));
        return "index";
    }
}
