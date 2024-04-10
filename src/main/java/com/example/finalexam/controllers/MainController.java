package com.example.finalexam.controllers;

import com.example.finalexam.model.Profile;
import com.example.finalexam.model.User;
import com.example.finalexam.model.enumProperties.Role;
import com.example.finalexam.services.CountryService;
import com.example.finalexam.services.ProfileService;
import com.example.finalexam.services.ResultService;
import com.example.finalexam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final CountryService countryService;
    private final ProfileService profileService;
    private final ResultService resultService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String FirstPage() {
        return "CommonPages/MainPage";
    }

    @GetMapping("/registration")
    public String reg(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "CommonPages/RegistrationPage";
    }

    @PostMapping("/registration")
    public String reg(Model model, Profile profile, User user) {
        register(profile, user);
        return "redirect:/";
    }

    private void register(Profile profile, User user) {
        Profile profileToSave = profileService.save(profile);
        user.setProfile(profileToSave);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        profileToSave.setUser(userService.save(user));
        profileService.save(profileToSave);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        System.out.println("this is login");
        return "CommonPages/LoginPage";

    }

//    @PostMapping("/login")
//    public String login( Model model,User user) { //вход в аккаунт
//        model.addAttribute("id",user.getProfile().getId());
//        return "redirect:/user/{id}";
//    }

//    @PreAuthorize()
//    @GetMapping("/admin")
//    public String adminRedirect() {
//        return "redirect:/admin";
//    }
//
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/user/account/{id}")
//    public String userRedirect(@PathVariable Long id) {
//        return "redirect:/user/account/" + id;
//    }


}
