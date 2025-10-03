package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoRepositorio extends JpaRepository<Empleado,Integer> {

    List<Empleado> findBySede( String sede);
    List<Empleado> findByCargo(String cargo);
}
