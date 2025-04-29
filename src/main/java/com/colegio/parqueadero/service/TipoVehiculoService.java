package com.colegio.parqueadero.service;

import com.colegio.parqueadero.model.TipoVehiculo;
import com.colegio.parqueadero.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVehiculoService {

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    public List<TipoVehiculo> findAll() {
        return tipoVehiculoRepository.findAll();
    }

    public Optional<TipoVehiculo> findById(Long id) {
        return tipoVehiculoRepository.findById(id);
    }

    public TipoVehiculo save(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    public void deleteById(Long id) {
        tipoVehiculoRepository.deleteById(id);
    }
}