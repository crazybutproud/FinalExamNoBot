package com.example.finalexam.repositories;

import com.example.finalexam.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    Optional<Profile> findByName(String name); //может лучше по фамилии?
    Profile findProfileByUser_Id(int id);

}
