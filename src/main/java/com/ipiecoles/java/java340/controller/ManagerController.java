package com.ipiecoles.java.java340.controller;

import com.ipiecoles.java.java340.model.Manager;
import com.ipiecoles.java.java340.service.EmployeService;
import com.ipiecoles.java.java340.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequestMapping("/managers")
@Controller
public class ManagerController{

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmployeService employeService;


    @GetMapping("/{id}/techniciens/{idTech}/delete")
    public String deleteTechnicien(@PathVariable("id") Long id, @PathVariable("idTech") Long idTech, Map<String, Object> model){
        managerService.deleteTechniciens(id, idTech);
        model.put("model", employeService.findById(id));
        return "employes/detail";
    }

    @GetMapping("/{id}/techniciens/add")
    public RedirectView deleteTechnicien(@PathVariable("id") Long id, @RequestParam("matricule") String matricule, Map<String, Object> model){
        managerService.addTechniciens(id, matricule);
        return new RedirectView("/employes/" + id);
    }

    @PostMapping("/{id}")
    public RedirectView save(@PathVariable(name = "id") Long id, Manager employe, Map<String, Object> model, RedirectAttributes attributes) {
        if(employe != null){
            employe = this.employeService.updateEmploye(id, employe);
        }
        model.put("model", employe);
        attributes.addAttribute("success", "Modifications enregistrées !");

        return new RedirectView("/employes/" + id);
    }

    @PostMapping("/save")
    public RedirectView saveNew(Manager employe, Map<String, Object> model, RedirectAttributes attributes) {
        employe = this.employeService.creerEmploye(employe);
        model.put("model", employe);
        attributes.addAttribute("success", "Création du commercial enregistrée !");

        return new RedirectView("/employes/ " + employe.getId());
    }
}
