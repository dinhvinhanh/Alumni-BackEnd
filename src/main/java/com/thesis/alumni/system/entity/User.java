package com.thesis.alumni.system.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "job_history")
    private String jobHistory;


    @Column(name = "workplace")
    private String workplace;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "job")
    private String job;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "data_of_birth")
    private Date dateOfBirth;

    @Column(name = "class_name")
    private String className;


    @Column(name = "salaryRange")
    private String salaryRange;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Role> roles;

    public User(String id, String name, String email, String password, Boolean active, List<Role> roles, String salaryRange, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.salaryRange = salaryRange;
        this.status = status;
    }
}
