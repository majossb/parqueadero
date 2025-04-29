package com.colegio.parqueadero.controller.api;


import com.colegio.parqueadero.model.RegistroVehiculo;
import com.colegio.parqueadero.service.RegistroVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@Tag(name = "Registro de Vehículos", description = "API para gestionar registros de vehículos")
public class RegistroVehiculoController {

    @Autowired
    private RegistroVehiculoService registroVehiculoService;

    @GetMapping
    @Operation(summary = "Obtener todos los registros", description = "Devuelve una lista de todos los registros de vehículos")
    public List<RegistroVehiculo> getAllRegistros() {
        return registroVehiculoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener registro por ID", description = "Devuelve un registro específico por su ID")
    public ResponseEntity<RegistroVehiculo> getRegistroById(@PathVariable Long id) {
        return registroVehiculoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Crear nuevo registro", description = "Crea un nuevo registro de vehículo (solo ADMINISTRADOR)")
    public ResponseEntity<RegistroVehiculo> createRegistro(@RequestBody RegistroVehiculo registroVehiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroVehiculoService.save(registroVehiculo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'ACOMODADOR')")
    @Operation(summary = "Actualizar registro", description = "Actualiza un registro existente (ADMINISTRADOR o ACOMODADOR)")
    public ResponseEntity<RegistroVehiculo> updateRegistro(@PathVariable Long id, @RequestBody RegistroVehiculo registroVehiculo) {
        return registroVehiculoService.findById(id)
                .map(existingRegistro -> {
                    registroVehiculo.setId(id);
                    return ResponseEntity.ok(registroVehiculoService.save(registroVehiculo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/ubicacion")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'ACOMODADOR')")
    @Operation(summary = "Actualizar ubicación", description = "Actualiza solo la ubicación de un vehículo (ADMINISTRADOR o ACOMODADOR)")
    public ResponseEntity<RegistroVehiculo> updateUbicacion(@PathVariable Long id, @RequestParam String ubicacion) {
        return registroVehiculoService.findById(id)
                .map(existingRegistro -> {
                    existingRegistro.setUbicacion(ubicacion);
                    return ResponseEntity.ok(registroVehiculoService.save(existingRegistro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/salida")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Registrar salida", description = "Registra la hora de salida de un vehículo (solo ADMINISTRADOR)")
    public ResponseEntity<RegistroVehiculo> registrarSalida(@PathVariable Long id, @RequestParam Integer horaSalida) {
        return registroVehiculoService.findById(id)
                .map(existingRegistro -> {
                    existingRegistro.setHoraSalida(horaSalida);
                    return ResponseEntity.ok(registroVehiculoService.save(existingRegistro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Eliminar registro", description = "Elimina un registro de vehículo (solo ADMINISTRADOR)")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Long id) {
        return registroVehiculoService.findById(id)
                .map(existingRegistro -> {
                    registroVehiculoService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehiculos-en-parqueadero")
    @Operation(summary = "Vehículos en parqueadero", description = "Obtiene la lista de vehículos que aún están en el parqueadero")
    public List<RegistroVehiculo> getVehiculosEnParqueadero() {
        return registroVehiculoService.findVehiculosEnParqueadero();
    }
}