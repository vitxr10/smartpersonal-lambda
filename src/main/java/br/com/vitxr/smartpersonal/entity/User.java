package br.com.vitxr.smartpersonal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private double height;
    private double weight;
    private boolean active;

}
