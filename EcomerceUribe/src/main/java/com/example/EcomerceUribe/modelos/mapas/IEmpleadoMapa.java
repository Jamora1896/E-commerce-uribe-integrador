package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel= "spring")
public interface IEmpleadoMapa {

    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "sede", target = "sede")
    EmpleadoDTO convertir_empleado_a_empleadodto(Empleado empleado);


    List<EmpleadoDTO> convetir_lista_a_listaempleadodto(List<Empleado> lista);
}
