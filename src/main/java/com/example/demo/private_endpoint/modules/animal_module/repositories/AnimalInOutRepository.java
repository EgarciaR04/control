package com.example.demo.private_endpoint.modules.animal_module.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.animal_module.models.AnimalInOut;

public interface AnimalInOutRepository extends JpaRepository<AnimalInOut, Long> {

    @Query("SELECT aio FROM AnimalInOut aio WHERE aio.user.company.id = :id_asig")
    List<AnimalInOut> findReportAnimalInOut(@Param("id_asig") long id);

}
