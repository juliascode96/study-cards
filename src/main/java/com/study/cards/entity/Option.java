package com.study.cards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "options")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 5000)
    private String answer;
    private Boolean correct;

    private boolean deleted = false;
}
