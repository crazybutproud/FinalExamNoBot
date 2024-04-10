package com.example.finalexam.services;

import com.example.finalexam.model.Feedback;
import com.example.finalexam.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

//    @Autowired
//    public FeedbackService(FeedbackRepository feedbackRepository) {
//        this.feedbackRepository = feedbackRepository;
//    }

    public List<Feedback> findAll() { //вывод всех профилей
        return (List<Feedback>) feedbackRepository.findAll();
    }

    public Feedback findOne(int id) { //вывод профиля по айди
        return feedbackRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Feedback feedback) {//добавление профиля
        feedbackRepository.save(feedback);
    }

    @Transactional
    public void update(int id, Feedback feedback) { //обновление данных профиля
        feedback.setId(id);
        feedbackRepository.save(feedback);
    }

    @Transactional
    public void delete(int id) { //удаление профиля
        feedbackRepository.deleteById(id);
    }
}
