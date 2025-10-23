package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoServicio {


    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa mapa;

    public EmpleadoDTO guardarEmpleado(Empleado datosEmpleado){
        if(datosEmpleado.getCargo()==null  ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "El cargo del empleado es obligatorio"
            );
        }
        Empleado empleadoQueGuardoElRepo=this.repositorio.save(datosEmpleado);
        if(empleadoQueGuardoElRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el empleado en la base de datos"
            );

        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoQueGuardoElRepo);
    }

}
