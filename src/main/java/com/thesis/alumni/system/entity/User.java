package com.thesis.alumni.system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

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

    @Column(name = "salaryRange")
    private Float salaryRange;

    @ManyToMany(cascade=CascadeType.MERGE)
    private List<Role> roles;

    public User(String id, String name, String email, String password, Boolean active, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
