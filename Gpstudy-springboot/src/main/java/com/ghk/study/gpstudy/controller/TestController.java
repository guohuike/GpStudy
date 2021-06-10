package com.ghk.study.gpstudy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource(value = "application1.properties")
public class TestController {
    @Value("${server.ports}")
    String port;
}
