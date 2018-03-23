package com.kk.springbootjpa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.kk
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.put("message","www.baidu.com");
        return  "hello";
    }

    @RequestMapping("/getIndex")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/home")
    public String showHome(){
        return "layout";
    }

}
