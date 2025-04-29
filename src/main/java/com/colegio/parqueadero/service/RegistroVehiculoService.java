package com.colegio.parqueadero.service;


import com.colegio.parqueadero.model.RegistroVehiculo;
import com.colegio.parqueadero.repository.RegistroVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroVehiculoService {

    @Autowired
    private RegistroVehiculoRepository registroVehiculoRepository;

    public List<RegistroVehiculo> findAll() {
        return registroVehiculoRepository.findAll();
    }

    public Optional<RegistroVehiculo> findById(Long id) {
        return registroVehiculoRepository.findById(id);
    }

    public RegistroVehiculo save(RegistroVehiculo registroVehiculo) {
        return registroVehiculoRepository.save(registroVehiculo);
    }

    public void deleteById(Long id) {
        registroVehiculoRepository.deleteById(id);
    }

    public List<RegistroVehiculo> findByPlaca(String placa) {
        return registroVehiculoRepository.findByPlaca(placa);
    }

    public List<RegistroVehiculo> findVehiculosEnParqueadero() {
        return registroVehiculoRepository.findByHoraSalidaIsNull();
    }
}