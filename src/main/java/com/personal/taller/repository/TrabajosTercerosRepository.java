package com.personal.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.personal.taller.dto.TrabajosTercerosDto;

@Repository
public interface TrabajosTercerosRepository extends JpaRepository<TrabajosTercerosDto, Long> {

}
