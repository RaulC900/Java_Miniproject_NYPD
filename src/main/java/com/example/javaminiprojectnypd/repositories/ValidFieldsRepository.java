package com.example.javaminiprojectnypd.repositories;

import com.example.javaminiprojectnypd.models.ValidFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidFieldsRepository extends JpaRepository<ValidFields, String> {
    List<ValidFields> findByCategory(String category);
}
