package com.example.finalexam.services;

import com.example.finalexam.model.Profile;
import com.example.finalexam.repositories.ProfileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<Profile> findAll() { //вывод всех профилей
        return (List<Profile>) profileRepository.findAll();
    }

    public Profile findOne(int id) { //вывод профиля по айди
        return profileRepository.findById(id).orElse(null);
    }

    @Transactional
    public Profile save(Profile profile) {//добавление профиля
        return profileRepository.save(profile);
    }

    public Profile findByName(String name) {
        return profileRepository.findByName(name).orElse(null);
    }
    public Profile findProfileByUser_Id(int id){
        return profileRepository.findProfileByUser_Id(id); //нужен для показа личного профиля после логина
    }
    @Transactional
    public void update(int id, Profile profile) { //обновление данных профиля
        profile.setId(id);
        profileRepository.save(profile);
    }

    @Transactional
    public void delete(int id) { //удаление профиля
        profileRepository.deleteById(id);
    }
}
