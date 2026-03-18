package com.personal.taller.request;

public class DetalleRepuestoRequest {

    private String descripcion;
    private long porcentajeRecargo;
    private long valor;
    private long cantidad;
    private long total;
    private long repuesto_id;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPorcentajeRecargo() {
        return porcentajeRecargo;
    }

    public void setPorcentajeRecargo(long porcentajeRecargo) {
        this.porcentajeRecargo = porcentajeRecargo;
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

    public long getRepuesto_id() {
        return repuesto_id;
    }

    public void setRepuesto_id(long repuesto_id) {
        this.repuesto_id = repuesto_id;
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
