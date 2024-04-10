package com.example.finalexam.model;

import com.example.finalexam.model.enumProperties.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
//            ,cascade = CascadeType.ALL)
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Profile profile;


    public User(String username, String password) {
        this.role = Role.ROLE_USER;
        this.username = username;
        this.password = password;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", profile=" + profile +
                '}';
    }
}
