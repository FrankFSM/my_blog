package com.ralap.blog.controller;

import com.ralap.blog.bussiness.annotation.BusinessLog;
import com.ralap.blog.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 页面相关渲染
 *
 * @author: ralap
 * @date: created at 2018/6/14 19:20
 */
@Slf4j
@Controller
public class RenderController {


    @BusinessLog("进入首页")
    @GetMapping("/")
    public ModelAndView home() {
        return ResultUtil.view("index");
    }

    @BusinessLog("进入首页")
    @GetMapping("/index")
    public ModelAndView index() {
        return ResultUtil.view("index");
    }

    @BusinessLog("进入发布文章页")
    @GetMapping("/article/publish")
    public ModelAndView publish() {
        return ResultUtil.view("article/publish");
    }

    @BusinessLog("进入文章修改页")
    @GetMapping("/article/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return ResultUtil.view("article/publish");
    }

    @BusinessLog("进入登录页")
    @GetMapping("/login")
    public ModelAndView login() {
        return ResultUtil.view("login");
    }

    @BusinessLog("进入图标页")
    @GetMapping("/icons")
    public ModelAndView icons() {
        return ResultUtil.view("icons");
    }

}


