package com.example.finalexam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "countries_info")
@NoArgsConstructor
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "country")
    private String country;
    @Column(name = "capital")
    private String capital;
    @Column(name = "best_time")
    private String bestTime;
    @Column(name = "sea")
    private boolean sea;
    @Column(name = "mountain")
    private boolean mountain;
    @Column(name = "tem")
    private int tem;
    @Column(name = "tem_sea")
    private int seaTem;

//    @ManyToOne
//    @JoinColumn(name = "profile_id", referencedColumnName = "id" //изменила айди
//            ,insertable=false, updatable=false)
//    private Profile profile;

    public Country(String country, String capital, String bestTime, boolean sea, boolean mountain, int tem, int seaTem) {
        this.country = country;
        this.capital = capital;
        this.bestTime = bestTime;
        this.sea = sea;
        this.mountain = mountain;
        this.tem = tem;
        this.seaTem = seaTem;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", capital='" + capital + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", sea=" + sea +
                ", mountain=" + mountain +
                ", tem=" + tem +
                ", seaTem=" + seaTem +
                '}';
    }
}
