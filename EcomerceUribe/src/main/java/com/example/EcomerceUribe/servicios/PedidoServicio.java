package com.example.EcomerceUribe.servicios;


import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa mapa;

    public PedidoDTO guardarPedido(Pedido datosPedido){

        if(datosPedido.getMontoTotal()==null || datosPedido.getMontoTotal() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "El monto total del pedido es obligatorio"
            );
        }
        Pedido pedidoQueGuardoElRepo=this.repositorio.save(datosPedido);
        if(pedidoQueGuardoElRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido en la base de datos"
            );

        }

        return this.mapa.convertir_pedido_a_pedidodto(pedidoQueGuardoElRepo);

    }
    //Buscar todos los pedidos
    public List<PedidoDTO> buscarTodosLosPedidos(){
        List<Pedido> listaDePedidosConsultados=this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listapedidodto(listaDePedidosConsultados);
    }

    //Buscar por Id
    public  PedidoDTO buscarPedidoPorId (Integer id){
        Optional<Pedido> pedidoQueEstoyBuscando=this.repositorio.findById(id);
        if(!pedidoQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id "+id+" suministrado"
            );
        }
        Pedido pedidoEncontrado = pedidoQueEstoyBuscando.get();
        return this.mapa.convertir_pedido_a_pedidodto(pedidoEncontrado);
    }


    //Eliminar un pedido por fecha de creación
    public void eliminarPedido(LocalDate fechaCreacion){
        List<Pedido> pedidoQueEstoyBuscando=this.repositorio.findByFechaCreacion(fechaCreacion);
        if(!pedidoQueEstoyBuscando.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con la fecha " + fechaCreacion + " suministrado"
            );
        }
        Pedido pedidoEncontrado = pedidoQueEstoyBuscando.get(0);
        try {
            this.repositorio.delete(pedidoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el pedido" +error.getMessage()
            );

        }

    }
    // Modificar algunos datos
    public PedidoDTO actualizarPedido (Integer id, Pedido datosActualizados) {
        Optional<Pedido> pedidoQueEstoyBuscando = this.repositorio.findById(id);
        if (!pedidoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun pedido con el id " + id + " suministrado"
            );

        }
        Pedido pedidoEncontrado = pedidoQueEstoyBuscando.get();

        // aplicar validaciones


        // actualizo los campos que se permitieron modificar

        pedidoEncontrado.setMontoTotal(datosActualizados.getMontoTotal());
        pedidoEncontrado.setFechaCreacion(datosActualizados.getFechaCreacion());


        //concluyo la operacion en la bd
        Pedido pedidoActualizado = this.repositorio.save(pedidoEncontrado);


        if (pedidoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el usuario en la base de datos, intenta nuevamente"
            );

        }

        return this.mapa.convertir_pedido_a_pedidodto(pedidoActualizado);
    }

}
