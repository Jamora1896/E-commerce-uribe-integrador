package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    //Buscar todos los empleados
    public List<EmpleadoDTO> buscarTodosLosEmpleados(){
        List<Empleado> listaDeEmpleadosConsultados=this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listaempleadodto(listaDeEmpleadosConsultados);
    }

    //Buscar un usuario por Id


    //Eliminar un usuario


    // Modificar algunos datos de un usuario

}
