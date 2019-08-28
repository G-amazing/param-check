package com.gamazing.controller;

import com.gamazing.vo.HelloVo;
import com.gamazing.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/hello")
    public Response hello(@RequestBody HelloVo.ReqHello request, BindingResult b) {
        return Response.success(messageSource,request.getNum());
    }
}
