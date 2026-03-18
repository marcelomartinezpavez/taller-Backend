package com.personal.taller.dto;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "trabajosGenerales")
public class TrabajosGeneralesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "porcentajeRecargo")
    private long porcentajeRecargo;
    @Column(name = "valor")
    private long valor;
    @Column(name = "cantidad")
    private long cantidad; /* Siempre debe ser 1 */
    @Column(name = "total")
    private long total;

    @Column(name = "prestadorServicio")
    private String prestadorServicio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenTrabajo_id")
    private OrdenTrabajoDto ordenTrabajo;

    public TrabajosGeneralesDto() {
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return long return the porcentajeRecargo
     */
    public long getPorcentajeRecargo() {
        return porcentajeRecargo;
    }

    /**
     * @param porcentajeRecargo the porcentajeRecargo to set
     */
    public void setPorcentajeRecargo(long porcentajeRecargo) {
        this.porcentajeRecargo = porcentajeRecargo;
    }

    /**
     * @return long return the valor
     */
    public long getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(long valor) {
        this.valor = valor;
    }

    /**
     * @return long return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return OrdenTrabajoDto return the ordenTrabajo
     */
    public OrdenTrabajoDto getOrdenTrabajo() {
        return ordenTrabajo;
    }

    /**
     * @param ordenTrabajo the ordenTrabajo to set
     */
    public void setOrdenTrabajo(OrdenTrabajoDto ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    /**
     * @return String return the prestadorServicio
     */
    public String getPrestadorServicio() {
        return prestadorServicio;
    }

    /**
     * @param prestadorServicio the prestadorServicio to set
     */
    public void setPrestadorServicio(String prestadorServicio) {
        this.prestadorServicio = prestadorServicio;
    }

    public long getCantidad() {
        return cantidad;
    }

    /**
     * @param valor the valor to set
     */
    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

}
