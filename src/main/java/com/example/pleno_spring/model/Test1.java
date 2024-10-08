package com.example.pleno_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test1")
@Data
public class Test1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}