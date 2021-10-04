package com.synclab.challenginatorUserService.appuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CONTROLLER DI TEST


@RestController
public class HelloController {

@RequestMapping("/user/hello")
    public String hello(){
    return "hello";
}
}

