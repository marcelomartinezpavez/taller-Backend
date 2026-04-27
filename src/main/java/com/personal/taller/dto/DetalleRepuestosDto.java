package com.personal.taller.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "detalleRepuestos")
public class DetalleRepuestosDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor")
    private long valor;
    @Column(name = "cantidad")
    private long cantidad = 1;
    @Column(name = "total")
    private long total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenTrabajo_id")
    @JsonBackReference
    private OrdenTrabajoDto ordenTrabajo;

    public DetalleRepuestosDto() {
    }

    @PrePersist
    public void prePersist() {
        if (this.cantidad == 0) {
            this.cantidad = 1;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public OrdenTrabajoDto getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDto ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}