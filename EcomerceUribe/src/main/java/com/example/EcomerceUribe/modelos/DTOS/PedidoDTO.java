package com.example.EcomerceUribe.modelos.DTOS;

import java.time.LocalDate;

public class PedidoDTO {

    private Integer montoTotal;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private Integer costoEnvio;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer montoTotal, LocalDate fechaCreacion, LocalDate fechaEntrega, Integer costoEnvio) {
        this.montoTotal = montoTotal;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.costoEnvio = costoEnvio;
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
