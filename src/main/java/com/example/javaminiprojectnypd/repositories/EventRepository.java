package com.example.javaminiprojectnypd.repositories;

import com.example.javaminiprojectnypd.models.Event;
import com.example.javaminiprojectnypd.models.ICountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT count(e) as count, e.offenseCode as offenseCode FROM Event e group by e.offenseCode")
    List<ICountDTO> countByOffenseCode();
    // Customized the result using Spring Data Projection
}
