package com.example.finalexam.services;

import com.example.finalexam.model.Result;
import com.example.finalexam.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {
    //кастомный запрос на получение страны по айди. получение данных о стране и о пользователе
    private final ResultRepository resultRepository;

    public List<Result> findAll() { //вывод всех юзеров
        return (List<Result>) resultRepository.findAll();
    } //вывод всех результатов

    public Result findOne(int code) {
        return resultRepository.findById(code)
                .orElse(null);
    }

    public Result findByProfileId(int id) {
        return resultRepository.findByProfileId(id).orElse(null);
    }

    @Transactional
    public Result save(Result result) {//сохранение результата после прохождения теста и получения идеальной страны. // void
//        resultRepository.save(result);

        return resultRepository.save(result);
    }

    @Transactional
    public void delete(int id) { //удаление результата
        resultRepository.deleteById(id);
    }


}
