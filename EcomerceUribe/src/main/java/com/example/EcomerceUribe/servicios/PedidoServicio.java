package com.example.EcomerceUribe.servicios;


import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    //Buscar un usuario por Id


    //Eliminar un usuario


    // Modificar algunos datos de un usuario

}
