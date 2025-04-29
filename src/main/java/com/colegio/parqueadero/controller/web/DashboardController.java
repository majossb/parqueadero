package com.colegio.parqueadero.controller.web;


import com.colegio.parqueadero.model.RegistroVehiculo;
import com.colegio.parqueadero.model.TipoVehiculo;
import com.colegio.parqueadero.service.RegistroVehiculoService;
import com.colegio.parqueadero.service.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private RegistroVehiculoService registroVehiculoService;

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("isAdmin", authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMINISTRADOR")));
        model.addAttribute("isAcomodador", authentication.getAuthorities().contains(new SimpleGrantedAuthority("ACOMODADOR")));
        model.addAttribute("username", authentication.getName());

        List<RegistroVehiculo> registros = registroVehiculoService.findAll();
        model.addAttribute("registros", registros);

        return "dashboard";
    }

    @GetMapping("/nuevo-registro")
    public String mostrarFormularioNuevoRegistro(Model model) {
        List<TipoVehiculo> tiposVehiculos = tipoVehiculoService.findAll();
        model.addAttribute("tiposVehiculos", tiposVehiculos);
        model.addAttribute("registro", new RegistroVehiculo());

        return "nuevo-registro";
    }

    @PostMapping("/guardar-registro")
    public String guardarRegistro(@ModelAttribute RegistroVehiculo registro) {
        registroVehiculoService.save(registro);
        return "redirect:/dashboard";
    }

    @GetMapping("/editar-registro/{id}")
    public String mostrarFormularioEditarRegistro(@PathVariable Long id, Model model) {
        registroVehiculoService.findById(id).ifPresent(registro -> {
            model.addAttribute("registro", registro);
            model.addAttribute("tiposVehiculos", tipoVehiculoService.findAll());
        });

        return "editar-registro";
    }

    @GetMapping("/registrar-salida/{id}")
    public String mostrarFormularioRegistrarSalida(@PathVariable Long id, Model model) {
        registroVehiculoService.findById(id).ifPresent(registro -> {
            model.addAttribute("registro", registro);
        });

        return "registrar-salida";
    }

    @PostMapping("/actualizar-ubicacion/{id}")
    public String actualizarUbicacion(@PathVariable Long id, @RequestParam String ubicacion) {
        registroVehiculoService.findById(id).ifPresent(registro -> {
            registro.setUbicacion(ubicacion);
            registroVehiculoService.save(registro);
        });

        return "redirect:/dashboard";
    }
}