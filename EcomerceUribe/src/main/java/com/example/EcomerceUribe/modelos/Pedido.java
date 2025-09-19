package com.example.EcomerceUribe.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer montoTotal;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private Integer costoEnvio;


    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference(value = "relacionpedidoproducto")
    private List<Producto> productos;


    @ManyToOne
    @JoinColumn(name = "fk_cliente", referencedColumnName = "id")
    @JsonBackReference(value = "relacionclientepedidolist")
    private Cliente cliente;



    public Pedido() {
    }

    public Pedido(Integer id, Integer montoTotal, LocalDate fechaCreacion, LocalDate fechaEntrega, Integer costoEnvio) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.costoEnvio = costoEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Integer costoEnvio) {
        this.costoEnvio = costoEnvio;
    }
}
