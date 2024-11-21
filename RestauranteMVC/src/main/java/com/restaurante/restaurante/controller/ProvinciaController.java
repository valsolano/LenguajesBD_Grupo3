package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Provincia;
import com.restaurante.restaurante.Service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;
    
    @GetMapping
    public String provincia(Model model) {
        model.addAttribute("provincias", provinciaService.obtenerProvincias());
        model.addAttribute("content", "provincia/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("provincia", new Provincia());
        model.addAttribute("content", "provincia/form");
        model.addAttribute("actionUrl", "/provincia/guardar");
        return "layout";
    }
    

    @PostMapping("/guardar")
    public String agregarProvincia(@ModelAttribute Provincia provincia, Model model) {
        System.out.println("entrooo agregar");
        provinciaService.insertarProvincia(provincia.getNombre());
        return "redirect:/direccion";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("provincia", provinciaService.obtenerProvinciaPorId(id));
        model.addAttribute("content", "provincia/form");
        model.addAttribute("actionUrl", "/provincia/actualizar");
        return "layout";
    }
    
    @PostMapping("/actualizar")
    public String actualizarProvincia(@ModelAttribute Provincia provincia, Model model) {
        provinciaService.actualizarProvincia(provincia.getIdProvincia(), provincia.getNombre(),provincia.getIdEstado());
        return "redirect:/direccion";
    }

   
    @GetMapping("/eliminar/{id}")
    public String eliminarProvincia(@PathVariable Integer id, Model model) {
        provinciaService.eliminarProvincia(id);
        return "redirect:/direccion";
    }
    

  
}
