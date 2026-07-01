package com.personal.taller.repository;

import com.personal.taller.dto.VehiculoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoDto, Long> {

    @Query(value = "select * from vehiculo v ", nativeQuery = true)
    List<VehiculoDto> findAllHabilitado();

    @Query(value = "select * from vehiculo v FULL JOIN clientes as c on v.rut_dueno = c.rut " +
            "where v.patente = :patente " , nativeQuery = true)
    VehiculoDto findByPatente(String patente);

    @Query(value = "select * from vehiculo v where v.rut_dueno = :rutCliente", nativeQuery = true)
    List<VehiculoDto> findByRutDueno(String rutCliente);

    // --- Métodos para cambio de patente ---

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE vehiculo_cliente SET vehiculo_patente = :newPatente WHERE vehiculo_patente = :oldPatente", nativeQuery = true)
    void updateVehiculoClientePatente(@Param("oldPatente") String oldPatente, @Param("newPatente") String newPatente);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE orden_trabajo SET vehiculo_patente = :newPatente WHERE vehiculo_patente = :oldPatente", nativeQuery = true)
    void updateOrdenTrabajoVehiculoPatente(@Param("oldPatente") String oldPatente, @Param("newPatente") String newPatente);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE orden_trabajo SET patente_vehiculo = :newPatente WHERE patente_vehiculo = :oldPatente", nativeQuery = true)
    void updateOrdenTrabajoPatenteVehiculo(@Param("oldPatente") String oldPatente, @Param("newPatente") String newPatente);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE agenda SET patente_vehiculo = :newPatente WHERE patente_vehiculo = :oldPatente", nativeQuery = true)
    void updateAgendaPatenteVehiculo(@Param("oldPatente") String oldPatente, @Param("newPatente") String newPatente);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE vehiculo SET patente = :newPatente WHERE patente = :oldPatente", nativeQuery = true)
    void updateVehiculoPatente(@Param("oldPatente") String oldPatente, @Param("newPatente") String newPatente);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM vehiculo WHERE patente = :patente", nativeQuery = true)
    void deleteByPatente(@Param("patente") String patente);
}
