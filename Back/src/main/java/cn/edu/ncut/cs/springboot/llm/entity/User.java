package cn.edu.ncut.cs.springboot.llm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    @Column(name="id")
    public int id;

    @JsonProperty(value = "username")
    @Column(name="username",unique = true, nullable = false)
    public String username;

    @JsonProperty(value = "password")
    @Column(name="password",nullable = false)
    public String password;
}