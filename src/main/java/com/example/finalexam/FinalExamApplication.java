package com.example.finalexam;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.IOException;

@SpringBootApplication
public class FinalExamApplication {

    public static void main(String[] args) throws TelegramApiException, IOException {
        SpringApplication.run(FinalExamApplication.class, args);
    }
}
