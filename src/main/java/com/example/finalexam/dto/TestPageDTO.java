package com.example.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TestPageDTO {
    private String season;
    private boolean sea;
    private boolean mountain;
    private int tem;
    private int temSea;

    @Override
    public String toString() {
        return "TestPageDTO{" +
                "season='" + season + '\'' +
                ", sea=" + sea +
                ", mountain=" + mountain +
                ", tem=" + tem +
                ", temSea=" + temSea +
                '}';
    }
}
