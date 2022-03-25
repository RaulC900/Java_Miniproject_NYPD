package com.example.javaminiprojectnypd.repositories;

import com.example.javaminiprojectnypd.models.EventDTO;
import com.example.javaminiprojectnypd.models.ICountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventDTO, Integer> {

    @Query("SELECT count(e) as count, e.offenseCode as offenseCode FROM EventDTO e group by e.offenseCode")
    List<ICountDTO> countByOffenseCode();
    // Customized the result using Spring Data Projection
}
