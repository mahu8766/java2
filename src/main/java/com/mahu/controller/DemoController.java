package com.mahu.controller;


import javax.annotation.Resource;
import com.mahu.model.People;
import com.mahu.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController {

    @Resource(name = "demoService")
    private DemoService demoService;

    @RequestMapping("/test")
    public String test() {
        People p = demoService.newPeople();
        demoService.save(p);
        return "test";
    }
}

