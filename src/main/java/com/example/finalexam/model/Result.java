package com.example.finalexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @Column(name = "code")
    private int code;
    @Column(name = "profile_id")
    private int profileId;
    @Column(name = "country_id")
    private int countryId;


    public Result(int profileId, int countryId) {
        Random random = new SecureRandom();
        int randomCode = random.nextInt(900000) + 100000;
        this.code = randomCode;
        this.profileId = profileId;
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", profileId=" + profileId +
                ", countryId=" + countryId +
                '}';
    }
}
