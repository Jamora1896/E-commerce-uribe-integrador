package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.ayudas.Sedes;
import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    //Buscar  por Id
    public  EmpleadoDTO buscarEmpleadoPorId (Integer id){
        Optional<Empleado> empleadoQueEstoyBuscando=this.repositorio.findById(id);
        if(!empleadoQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id "+id+" suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoQueEstoyBuscando.get();
        return this.mapa.convertir_empleado_a_empleadodto(empleadoEncontrado);
    }

    //Eliminar Empleados por sede
    public void eliminarEmpleado(String sede){
        List<Empleado> empleadoQueEstoyBuscando=this.repositorio.findBySede(sede);
        if(!empleadoQueEstoyBuscando.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con la sede " + sede + " suministrado"
            );
        }
        Empleado empleadoEncontrado = empleadoQueEstoyBuscando.get(0); //preguntar al profe con listas
        try {
            this.repositorio.delete(empleadoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el pedido" +error.getMessage()
            );

        }

    }
    // Modificar algunos datos
    public EmpleadoDTO actualizarEmpleado(Integer id, Empleado datosActualizados) {
        Optional<Empleado> empleadoQueEstoyBuscando = this.repositorio.findById(id);
        if (!empleadoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun empleado con el id " + id + " suministrado"
            );

        }
        Empleado empleadoEncontrado = empleadoQueEstoyBuscando.get();

        // aplicar validaciones


        // actualizo los campos que se permitieron modificar

        empleadoEncontrado.setCargo(datosActualizados.getCargo());
        empleadoEncontrado.setSalario(datosActualizados.getSalario());


        //concluyo la operacion en la bd
        Empleado empleadoActualizado = this.repositorio.save(empleadoEncontrado);


        if (empleadoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar  en la base de datos, intenta nuevamente"
            );

        }

        return this.mapa.convertir_empleado_a_empleadodto(empleadoActualizado);
    }




}
