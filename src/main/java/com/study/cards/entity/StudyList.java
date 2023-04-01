package com.study.cards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lists")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class StudyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String tittle;

    private String coverImage;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Card> cards = new HashSet();

    private boolean shared;
    private boolean deleted = false;

}
