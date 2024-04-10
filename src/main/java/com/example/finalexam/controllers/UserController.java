package com.example.finalexam.controllers;

import com.example.finalexam.dto.TestPageDTO;
import com.example.finalexam.model.Country;
import com.example.finalexam.model.Feedback;
import com.example.finalexam.model.Result;
import com.example.finalexam.model.User;
import com.example.finalexam.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CountryService countryService;
    private final ProfileService profileService;
    private final ResultService resultService;
    private final FeedbackService feedbackService;


    @GetMapping("/{id}")
    public String account(@PathVariable("id") int id, Model model, User user) {
        model.addAttribute("id", id);
        model.addAttribute("name",
                profileService.findProfileByUser_Id(user.getId()).getName());
        model.addAttribute("surname",
                profileService.findProfileByUser_Id(user.getId()).getSurname());
        model.addAttribute("email",
                profileService.findProfileByUser_Id(user.getId()).getEmail());
        System.out.println("this is account");
        return "UserPages/UserAccountPage";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") int profileId, Model model) {
        model.addAttribute("id", profileId);
        model.addAttribute("testPageDTO", new TestPageDTO());
        System.out.println("this is test get");
        return "UserPages/TestPage";
    }

    @PostMapping("/test/{id}")
    public String test(@PathVariable("id") int profileId, RedirectAttributes redirectAttributes, TestPageDTO testPageDTO) { //@RequestBody
        redirectAttributes.addAttribute("id", profileId);
        System.out.println("this is test post");
        Country bestCountry = countryService.searchBest(testPageDTO); //ошибка вылезает на этом моменте
        System.out.println("this is test post searchBest");
        System.out.println("this is test post SaveResult");
        redirectAttributes.addAttribute("result", resultService.save(new Result(profileId,bestCountry.getId())));
        return "redirect:/user/result/" + profileId;
    }


    @GetMapping("/result/{id}")
    public String result(@PathVariable("id") int profileId, Model model, Result result) {
        model.addAttribute("id", profileId);
        int countryId = result.getCountryId();
        Country country = countryService.findOne(countryId);
        model.addAttribute("country", country.getCountry()); //параметры для сборки страницы
        model.addAttribute("capital", country.getCapital());
        model.addAttribute("bestTime", country.getBestTime());
        model.addAttribute("tem", country.getTem());
        model.addAttribute("code", result.getCode()); //уникальный код
        return "UserPages/ResultPage";
    }

    @GetMapping("/callback/{id}")
    public String callback(@PathVariable("id") int profileId, Model model, String number,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", profileId);
        Feedback feedback = new Feedback();
        model.addAttribute("number", number);
        model.addAttribute("feedback",feedback);
        return "UserPages/CallbackPage";
    }

    @PostMapping("/callback/{id}")
    public String callback(@PathVariable("id") int profileId, Model model, Feedback feedback, String number,RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", profileId);
        model.addAttribute("number", number);
        feedback.setNumber(number);
        feedback.setName(profileService.findProfileByUser_Id(profileId).getName()); //изменить метод на файнд бай юзер айди
        feedbackService.save(feedback);
        return "redirect:/user/" + profileId; //попытка редиректа на аккаунт
    }
}
