package com.personal.taller.request;

public class RepuestosOrdenRequest {

    private String descripcion;
    private long porcentajeRecargo;
    private long valor;
    private long total;
    private String prestadorServicio;
    private long cantidad;

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

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

}
