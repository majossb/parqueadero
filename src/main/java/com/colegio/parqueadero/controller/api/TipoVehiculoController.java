package com.colegio.parqueadero.controller.api;

import com.colegio.parqueadero.model.TipoVehiculo;
import com.colegio.parqueadero.service.TipoVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-vehiculos")
@Tag(name = "Tipos de Vehículos", description = "API para gestionar tipos de vehículos")
public class TipoVehiculoController {

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping
    @Operation(summary = "Obtener todos los tipos", description = "Devuelve una lista de todos los tipos de vehículos")
    public List<TipoVehiculo> getAllTipos() {
        return tipoVehiculoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo por ID", description = "Devuelve un tipo de vehículo específico por su ID")
    public ResponseEntity<TipoVehiculo> getTipoById(@PathVariable Long id) {
        return tipoVehiculoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Crear nuevo tipo", description = "Crea un nuevo tipo de vehículo (solo ADMINISTRADOR)")
    public ResponseEntity<TipoVehiculo> createTipo(@RequestBody TipoVehiculo tipoVehiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoVehiculoService.save(tipoVehiculo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Actualizar tipo", description = "Actualiza un tipo de vehículo existente (solo ADMINISTRADOR)")
    public ResponseEntity<TipoVehiculo> updateTipo(@PathVariable Long id, @RequestBody TipoVehiculo tipoVehiculo) {
        return tipoVehiculoService.findById(id)
                .map(existingTipo -> {
                    tipoVehiculo.setId(id);
                    return ResponseEntity.ok(tipoVehiculoService.save(tipoVehiculo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @Operation(summary = "Eliminar tipo", description = "Elimina un tipo de vehículo (solo ADMINISTRADOR)")
    public ResponseEntity<Void> deleteTipo(@PathVariable Long id) {
        return tipoVehiculoService.findById(id)
                .map(existingTipo -> {
                    tipoVehiculoService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}