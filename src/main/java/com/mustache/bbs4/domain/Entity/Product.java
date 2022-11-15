package com.mustache.bbs4.domain.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name2")
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdDate;
}
