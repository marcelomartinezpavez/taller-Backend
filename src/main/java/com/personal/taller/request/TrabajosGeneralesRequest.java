package com.personal.taller.request;

public class TrabajosGeneralesRequest {

    private String descripcionGeneral;
    private long porcentajeRecargoGeneral;
    private long valorGeneral;
    private long totalGeneral;
    private String prestadorServicioGeneral;

    /**
     * @return String return the descripcion
     */
    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    /**
     * @return long return the porcentajeRecargo
     */
    public long getPorcentajeRecargoGeneral() {
        return porcentajeRecargoGeneral;
    }

    /**
     * @param porcentajeRecargo the porcentajeRecargo to set
     */
    public void setPorcentajeRecargoGeneral(long porcentajeRecargoGeneral) {
        this.porcentajeRecargoGeneral = porcentajeRecargoGeneral;
    }

    /**
     * @return long return the valor
     */
    public long getValorGeneral() {
        return valorGeneral;
    }

    /**
     * @param valor the valor to set
     */
    public void setValorGeneral(long valorGeneral) {
        this.valorGeneral = valorGeneral;
    }

    /**
     * @return long return the total
     */
    public long getTotalGeneral() {
        return totalGeneral;
    }

    /**
     * @param total the total to set
     */
    public void setTotalGeneral(long totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    /**
     * @return String return the prestadorServicio
     */
    public String getPrestadorServicioGeneral() {
        return prestadorServicioGeneral;
    }

    /**
     * @param prestadorServicio the prestadorServicio to set
     */
    public void setPrestadorServicioGeneral(String prestadorServicioGeneral) {
        this.prestadorServicioGeneral = prestadorServicioGeneral;
    }

    private long cantidadGeneral;

    public long getCantidadGeneral() {
        return cantidadGeneral;
    }

    public void setCantidadGeneral(long cantidadGeneral) {
        this.cantidadGeneral = cantidadGeneral;
    }

}
