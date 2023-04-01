package com.study.cards.repository;

import com.study.cards.entity.StudyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyListRepository extends JpaRepository<StudyList, Long> {
}
