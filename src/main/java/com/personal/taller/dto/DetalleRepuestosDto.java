package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "detalleRepuestos")
public class DetalleRepuestosDto implements Serializable {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordenTrabajo_id")
    private OrdenTrabajoDto ordenTrabajo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repuesto_id")
    private RepuestoDto repuesto;

    public DetalleRepuestosDto() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrdenTrabajoDto getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDto ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public RepuestoDto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(RepuestoDto repuesto) {
        this.repuesto = repuesto;
    }

    public long getPorcentajeRecargo() {
        return porcentajeRecargo;
    }

    public void setPorcentajeRecargo(long porcentajeRecargo) {
        this.porcentajeRecargo = porcentajeRecargo;
    }

    /*
     * public OrdenTrabajoDto getOrdenTrabajoDto() {
     * return ordenTrabajoDto;
     * }
     * 
     * public void setOrdenTrabajoDto(OrdenTrabajoDto ordenTrabajoDto) {
     * this.ordenTrabajoDto = ordenTrabajoDto;
     * }
     */

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
     * @return long return the cantidad
     */
    public long getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
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

}
