package com.study.cards.service;

import com.study.cards.entity.Card;
import com.study.cards.entity.StudyList;
import com.study.cards.repository.CardRepository;
import com.study.cards.repository.StudyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@Service
public class StudyListService {

    @Autowired
    StudyListRepository studyListRepository;

    @Autowired
    CardRepository cardRepository;

    public StudyList saveList(StudyList list){
        return studyListRepository.save(list);
    }

    public List<StudyList> getAllLists(){
        return studyListRepository.findAll();
    }

    public StudyList findById(Long id) throws ServerException {
        StudyList list = studyListRepository.findById(id)
                .orElseThrow(()-> new ServerException("Entidad no encontrada"));

        return list;
    }

    public void deleteStudyList(Long id) throws Exception {
        if(studyListRepository.existsById(id)){
            studyListRepository.deleteById(id);
            return;
        }

        throw new ServerException("Lista no encontrada");
    }

    public StudyList updateList(StudyList list) throws Exception {
        if(studyListRepository.existsById(list.getId())){
            return studyListRepository.save(list);
        }

        throw new ServerException("Lista no encontrada");
    }

    public StudyList addNewCard(Card card, StudyList list) throws Exception {
        if(studyListRepository.existsById(list.getId())){

            if(cardRepository.existsById(card.getId())){
                list.getCards().add(card);
            }
            return studyListRepository.save(list);
        }

        throw new ServerException("Ha ocurrido un error");
    }
}
