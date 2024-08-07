package com.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String address;
    private String role;

    private String password;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "student_subject",
        joinColumns = @JoinColumn(name = "user_id"), // FK referencing 'User' entity
        inverseJoinColumns = @JoinColumn(name = "subject_id") // FK referencing 'Subject' entity
    )
    private Set<Subject> subjects = new HashSet<>();
}
