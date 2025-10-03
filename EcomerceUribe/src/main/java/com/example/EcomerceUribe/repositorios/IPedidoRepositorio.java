package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPedidoRepositorio extends JpaRepository<Pedido,Integer> {


    List<Pedido> findByFechaCreacion(LocalDate fechaCreacion);
}
