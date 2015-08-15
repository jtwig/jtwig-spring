package org.jtwig.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
    @RequestMapping("/")
    public String indexAction (ModelMap modelMap) {
        modelMap.addAttribute("name", "Jtwig");
        return "index";
    }
}
