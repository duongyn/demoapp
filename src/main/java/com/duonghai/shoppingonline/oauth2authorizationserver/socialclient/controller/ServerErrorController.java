package com.duonghai.shoppingonline.oauth2authorizationserver.socialclient.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    String existError(@RequestParam("username") String name) {
        return "User "+name+" exists in the database";
    }

}
