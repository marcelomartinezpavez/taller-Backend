package com.personal.taller.request;

public class TrabajosTercerosRequest {

    private String descripcionTercero;
    private long porcentajeRecargoTercero;
    private long valorTercero;
    private long totalTercero;
    private String prestadorServicioTercero;

    /**
     * @return String return the descripcion
     */
    public String getDescripcionTercero() {
        return descripcionTercero;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcionTercero(String descripcionTercero) {
        this.descripcionTercero = descripcionTercero;
    }

    /**
     * @return long return the porcentajeRecargo
     */
    public long getPorcentajeRecargoTercero() {
        return porcentajeRecargoTercero;
    }

    /**
     * @param porcentajeRecargo the porcentajeRecargo to set
     */
    public void setPorcentajeRecargoTercero(long porcentajeRecargoTercero) {
        this.porcentajeRecargoTercero = porcentajeRecargoTercero;
    }

    /**
     * @return long return the valor
     */
    public long getValorTercero() {
        return valorTercero;
    }

    /**
     * @param valor the valor to set
     */
    public void setValorTercero(long valorTercero) {
        this.valorTercero = valorTercero;
    }

    /**
     * @return long return the total
     */
    public long getTotalTercero() {
        return totalTercero;
    }

    /**
     * @param total the total to set
     */
    public void setTotalTercero(long totalTercero) {
        this.totalTercero = totalTercero;
    }

    /**
     * @return String return the prestadorServicio
     */
    public String getPrestadorServicioTercero() {
        return prestadorServicioTercero;
    }

    /**
     * @param prestadorServicio the prestadorServicio to set
     */
    public void setPrestadorServicioTercero(String prestadorServicioTercero) {
        this.prestadorServicioTercero = prestadorServicioTercero;
    }

    private long cantidadTercero;

    public long getCantidadTercero() {
        return cantidadTercero;
    }

    public void setCantidadTercero(long cantidadTercero) {
        this.cantidadTercero = cantidadTercero;
    }

}
