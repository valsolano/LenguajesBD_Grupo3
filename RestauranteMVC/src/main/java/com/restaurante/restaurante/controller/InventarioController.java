package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Service.InventarioService;
import com.restaurante.restaurante.Domain.Inventario;
import com.restaurante.restaurante.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private ProveedorService proveedorService;
    
    @GetMapping
    public String inventario(Model model) {
        model.addAttribute("inventarios", inventarioService.obtenerInventarios());
        model.addAttribute("proveedores",proveedorService.obtenerProveedores());
        model.addAttribute("content", "inventario/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("inventario", new Inventario());
        model.addAttribute("content", "inventario/form");
        model.addAttribute("actionUrl", "/inventario/guardar");
        return "layout";
    }

    
    
    @PostMapping("/guardar")
    public String agregarInventario(@ModelAttribute Inventario inventario, Model model) {
        System.out.println("entrooo agregar");
        inventarioService.insertarInventario( inventario.getNombre(), inventario.getDescripcion(),inventario.getPrecioCompra(),inventario.getStock(),inventario.getFechaCaducidad(),inventario.getIdProveedor());
        return "redirect:/inventario";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("inventario", inventarioService.obtenerInventarioPorId(id));
        model.addAttribute("content", "inventario/form");
        model.addAttribute("actionUrl", "/inventario/actualizar");
        return "layout";
    }
    
     @PostMapping("/actualizar")
    public String actualizarInventario(@ModelAttribute Inventario inventario, Model model) {
        inventarioService.actualizarInventario(inventario.getIdInventario(), inventario.getNombre(), inventario.getDescripcion(),inventario.getPrecioCompra(),inventario.getStock(),inventario.getFechaCaducidad(),inventario.getIdProveedor(),inventario.getIdEstado());
        return "redirect:/inventario";
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminarInventario(@PathVariable Integer id, Model model) {
        inventarioService.eliminarInventario(id);
        return "redirect:/inventario";
    }
    
   
    
}
