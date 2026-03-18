package com.personal.taller.request;

public class TrabajosTercerosRequest {

    private String descripcion;
    private long porcentajeRecargo;
    private long valor;
    private long cantidad;
    private long total;
    private long repuesto_id;
    private String prestadorServicio;

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

    /**
     * @return long return the repuesto_id
     */
    public long getRepuesto_id() {
        return repuesto_id;
    }

    /**
     * @param repuesto_id the repuesto_id to set
     */
    public void setRepuesto_id(long repuesto_id) {
        this.repuesto_id = repuesto_id;
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

}
