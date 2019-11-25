package com.rudy.ladl.controller;

import com.rudy.ladl.service.TopoService;
import com.rudy.ladl.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopoController {

    private TopoService topoService;
    public TopoController (TopoService topoService) {
        this.topoService = topoService;
    }

    @GetMapping(Constant.TOPOS_PATH)
    public String getAllSites(Model model) {
        model.addAttribute("topos", topoService.findAll());
        return Constant.TOPO_LIST_PAGE;
    }

}