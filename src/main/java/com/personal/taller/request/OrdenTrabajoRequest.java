package com.personal.taller.request;

import java.util.List;

public class OrdenTrabajoRequest {

    private long id;
    private String numeroOrden;
    private boolean habilitado;
    private String rutCliente;
    private String patenteVehiculo;
    private String codigo;
    private long valorOt;
    private String estado;

    private long idEmpresa;
    private List<DetalleRepuestoRequest> detalleRepuesto;
    private List<TrabajosGeneralesRequest> trabajosGenerales;
    private List<TrabajosTercerosRequest> trabajosTerceros;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public long getValorOt() {
        return valorOt;
    }

    public void setValorOt(long valorOt) {
        this.valorOt = valorOt;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DetalleRepuestoRequest> getDetalleRepuesto() {
        return detalleRepuesto;
    }

    public void setDetalleRepuesto(List<DetalleRepuestoRequest> detalleRepuesto) {
        this.detalleRepuesto = detalleRepuesto;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return List<TrabajosGeneralesRequest> return the trabajosGenerales
     */
    public List<TrabajosGeneralesRequest> getTrabajosGenerales() {
        return trabajosGenerales;
    }

    /**
     * @param trabajosGenerales the trabajosGenerales to set
     */
    public void setTrabajosGenerales(List<TrabajosGeneralesRequest> trabajosGenerales) {
        this.trabajosGenerales = trabajosGenerales;
    }

    /**
     * @return List<TrabajoTercerosRequest> return the trabajoTerceros
     */
    public List<TrabajosTercerosRequest> getTrabajosTerceros() {
        return trabajosTerceros;
    }

    /**
     * @param trabajoTerceros the trabajoTerceros to set
     */
    public void setTrabajosTerceros(List<TrabajosTercerosRequest> trabajosTerceros) {
        this.trabajosTerceros = trabajosTerceros;
    }

}
