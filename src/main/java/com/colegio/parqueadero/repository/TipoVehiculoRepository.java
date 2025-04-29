package com.colegio.parqueadero.repository;

import com.colegio.parqueadero.model.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {
}