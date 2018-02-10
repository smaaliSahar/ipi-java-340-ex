package com.ipiecoles.java.java340.controller;

import com.ipiecoles.java.java340.model.Manager;
import com.ipiecoles.java.java340.model.Technicien;
import com.ipiecoles.java.java340.service.EmployeService;
import com.ipiecoles.java.java340.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RequestMapping("/techniciens")
@Controller
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    @Autowired
    private EmployeService employeService;


    @PostMapping("/{id}")
    public RedirectView save(@PathVariable(name = "id") Long id, Technicien employe, Map<String, Object> model, RedirectAttributes attributes) {
        if(employe != null){
            employe = this.employeService.updateEmploye(id, employe);
        }
        model.put("model", employe);
        attributes.addAttribute("success", "Modifications enregistrées !");
        //return new ModelAndView("employes/detail", model);
        return new RedirectView("/employes/" + id);
    }

    @PostMapping("/save")
    public RedirectView saveNew(Technicien employe, Map<String, Object> model, RedirectAttributes attributes) {
        employe = this.employeService.creerEmploye(employe);
        model.put("model", employe);
        attributes.addAttribute("success", "Création du commercial enregistrée !");

        return new RedirectView("/employes/" + employe.getId());
    }

    @GetMapping("/{id}/manager/{idManager}/delete")
    public RedirectView removeManager(@PathVariable(name = "id") Long id, @PathVariable(name = "idManager") Long idManager){
        Technicien technicien = (Technicien) employeService.findById(id);
        if(technicien.getManager() != null && technicien.getManager().getId().equals(idManager)){
            technicien.setManager(null);
            this.employeService.updateEmploye(id, technicien);
        }
        else {
            //Erreur
        }

        return new RedirectView("/employes/" + id);
    }

    @GetMapping("/{id}/manager/add")
    public RedirectView addManager(@PathVariable(name = "id") Long id, @RequestParam(name = "matricule") String matricule){
        Technicien technicien = (Technicien) employeService.findById(id);
        Manager manager = (Manager) employeService.findByMatricule(matricule);
        technicien.setManager(manager);
        this.employeService.updateEmploye(id, technicien);

        return new RedirectView("/employes/" + id);
    }
}
