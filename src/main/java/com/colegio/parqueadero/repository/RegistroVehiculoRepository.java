package com.colegio.parqueadero.repository;


import com.colegio.parqueadero.model.RegistroVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroVehiculoRepository extends JpaRepository<RegistroVehiculo, Long> {
    List<RegistroVehiculo> findByPlaca(String placa);
    List<RegistroVehiculo> findByHoraSalidaIsNull();
}