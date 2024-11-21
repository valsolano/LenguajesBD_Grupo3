/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Direccion;
import com.restaurante.restaurante.Service.CantonService;
import com.restaurante.restaurante.Service.DireccionService;
import com.restaurante.restaurante.Service.DistritoService;
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
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private CantonService cantonService;
    @Autowired
    private DistritoService distritoService;

    @GetMapping
    public String direccion(Model model) {
        model.addAttribute("direcciones", direccionService.obtenerDirecciones());
        model.addAttribute("provincias", provinciaService.obtenerProvincias());
        model.addAttribute("cantones", cantonService.obtenerCantones());
        model.addAttribute("distritos", distritoService.obtenerDistritos());
        model.addAttribute("content", "direccion/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("direccion", new Direccion());
        model.addAttribute("content", "direccion/form");
        model.addAttribute("actionUrl", "/direccion/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarDireccion(@ModelAttribute Direccion direccion, Model model) {
        System.out.println("entrooo agregar");
        direccionService.insertarDireccion(direccion.getIdProvincia(), direccion.getIdCanton(), direccion.getIdDistrito(), direccion.getDireccion());
        return "redirect:/direccion";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("direccion", direccionService.obtenerDireccionPorId(id));
        model.addAttribute("content", "direccion/form");
        model.addAttribute("actionUrl", "/direccion/actualizar");
        return "layout";
    }

    @PostMapping("direccion/actualizar")
    public String actualizarDireccion(@ModelAttribute Direccion direccion, Model model) {
        direccionService.actualizarDireccion(direccion.getIdDireccion(), direccion.getIdProvincia(), direccion.getIdCanton(), direccion.getIdDistrito(), direccion.getIdEstado(), direccion.getDireccion());
        return "redirect:/direccion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDireccion(@PathVariable Integer id, Model model) {
        direccionService.eliminarDireccion(id);
        return "redirect:/direccion";
    }

}
