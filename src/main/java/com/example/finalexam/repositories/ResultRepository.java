package com.example.finalexam.repositories;

import com.example.finalexam.model.Country;
import com.example.finalexam.model.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    Optional<Result> findByProfileId(int id);

}
