package com.example.finalexam.controllers;

import com.example.finalexam.model.Result;
import com.example.finalexam.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final CountryService countryService;
    private final ProfileService profileService;
    private final ResultService resultService;
    private final FeedbackService feedbackService;


    @GetMapping("/")
    public String showAdminPage(Model model) {
        //основна страничка админа.3 кнопки для перехода на нужные странички,таблица с запросами на обратную связь
        model.addAttribute("feedbacks", feedbackService.findAll());
        return "AdminPages/AdminPage";
    }

    @GetMapping("/AllResults")
    public String showAdminResults(Model model) {
        //страница со списком всех результатов пользователей
        model.addAttribute("results", resultService.findAll());
        return "AdminPages/ResultsAdminPage";
    }


    @GetMapping("/AllProfiles")
    public String showAdminProfiles(Model model) {
        //страница со списком пользователей
        model.addAttribute("profiles", profileService.findAll());
        return "AdminPages/ProfilesAdminPage";
    }

}
