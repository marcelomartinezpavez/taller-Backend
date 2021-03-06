package com.personal.taller.repository;

import com.personal.taller.dto.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDto, Long> {

    @Override
    Optional<ClienteDto> findById(Long aLong);

    @Query(value = "select * from clientes c where c.rut = :rut", nativeQuery = true)
    Optional<ClienteDto> findByRutAndHabilitado(String rut);

    @Query(value = "select * from clientes c where c.empresa_id = :idEmpresa", nativeQuery = true)
    List<ClienteDto> findByEmpresaId(long idEmpresa);

    @Query(value = "select * from clientes c ", nativeQuery = true)
    List<ClienteDto> findAllHabilitado();

    @Query(value = "select * from clientes c where c.rut = :rut and c.empresa_id = :empresa_id", nativeQuery = true)
    Optional<ClienteDto> findByRutAndHabilitadoAndEmpresa(String rut, long empresa_id);


}
