package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDto, Long> {

    @Override
    Optional<ClienteDto> findById(Long aLong);

    @Query(value = "select * from clientes c where c.rut = :rut", nativeQuery = true)
    Optional<ClienteDto> findByRutAndHabilitado(String rut);

    @Query(value = "select * from clientes c ", nativeQuery = true)
    List<ClienteDto> findAllHabilitado();

    // --- Métodos para cambio de RUT ---

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE vehiculo_cliente SET cliente_rut = :newRut WHERE cliente_rut = :oldRut", nativeQuery = true)
    void updateVehiculoClienteRut(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE orden_trabajo SET cliente_rut = :newRut WHERE cliente_rut = :oldRut", nativeQuery = true)
    void updateOrdenTrabajoClienteRut(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE orden_trabajo SET rut_cliente = :newRut WHERE rut_cliente = :oldRut", nativeQuery = true)
    void updateOrdenTrabajoRutCliente(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE agenda SET rut_cliente = :newRut WHERE rut_cliente = :oldRut", nativeQuery = true)
    void updateAgendaRutCliente(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE vehiculo SET rut_dueno = :newRut WHERE rut_dueno = :oldRut", nativeQuery = true)
    void updateVehiculoRutDueno(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE clientes SET rut = :newRut WHERE rut = :oldRut", nativeQuery = true)
    void updateClienteRut(@Param("oldRut") String oldRut, @Param("newRut") String newRut);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM clientes WHERE rut = :rut", nativeQuery = true)
    void deleteByRut(@Param("rut") String rut);
}
