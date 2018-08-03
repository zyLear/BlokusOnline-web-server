package com.zylear.blokus.wsserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/8/3.
 */
@Controller
@RequestMapping("/one")
public class TestHtml {

    @RequestMapping("/test")
    public ModelAndView string() {
        return new ModelAndView("One");
    }

    @RequestMapping("/test1")
    public String strings() {
        return "One";
    }

}
