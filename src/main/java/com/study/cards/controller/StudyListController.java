package com.study.cards.controller;

import com.study.cards.entity.Card;
import com.study.cards.entity.StudyList;
import com.study.cards.service.CardService;
import com.study.cards.service.StudyListService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/studyList")
public class StudyListController {

    @Autowired
    StudyListService studyListService;

    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity<StudyList> newStudyList(@RequestBody StudyList StudyList) throws ParseException {
        return new ResponseEntity<>(studyListService.saveList(StudyList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudyList>> getAll() {
        return new ResponseEntity<>(studyListService.getAllLists(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyList> getStudyList(@PathVariable @NotNull Long id) throws ServerException {
        return new ResponseEntity<StudyList>(studyListService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyList> updateStudyList(
            @PathVariable @NotNull Long id,
            @RequestBody StudyList StudyList
    ) throws Exception {
        return new ResponseEntity<StudyList>(studyListService.updateList(StudyList), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudyList(
            @PathVariable @NotNull Long id
    ) throws Exception {
        studyListService.deleteStudyList(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/card/{cardId}")
    public ResponseEntity<StudyList> addNewOption(
            @PathVariable @NotNull Long id,
            @PathVariable @NotNull Long cardId
    ) throws Exception {

        Card card = cardService.findById(cardId);
        StudyList studyList = studyListService.findById(id);

        return new ResponseEntity<StudyList>(studyListService.addNewCard(card, studyList), HttpStatus.CREATED);
    }
}
