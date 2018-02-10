package com.ipiecoles.java.java340.controller;

import com.ipiecoles.java.java340.model.Commercial;
import com.ipiecoles.java.java340.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequestMapping("/commercials")
@Controller
public class CommercialController {

    @Autowired
    private EmployeService employeService;


    @PostMapping("/{id}")
    public RedirectView save(@PathVariable(name = "id") Long id, Commercial employe, Map<String, Object> model, RedirectAttributes attributes) {
        if(employe != null){
            employe = this.employeService.updateEmploye(id, employe);
        }
        model.put("model", employe);
        attributes.addAttribute("success", "Modifications enregistrées !");

        return new RedirectView("/employes/" + id);
    }

    @PostMapping("/save")
    public RedirectView saveNew(Commercial employe, Map<String, Object> model, RedirectAttributes attributes) {
        employe = this.employeService.creerEmploye(employe);
        model.put("model", employe);
        attributes.addAttribute("success", "Création du commercial enregistrée !");

        return new RedirectView("/employes/" + employe.getId());    }
}
