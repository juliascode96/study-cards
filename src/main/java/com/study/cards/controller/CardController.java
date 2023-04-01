package com.study.cards.controller;

import com.study.cards.entity.Card;
import com.study.cards.entity.Option;
import com.study.cards.service.CardService;
import com.study.cards.service.OptionService;
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
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    OptionService optionService;

    @PostMapping
    public ResponseEntity<Card> newCard(@RequestBody Card card) throws ParseException {
        return new ResponseEntity<>(cardService.saveCard(card), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAll() {
        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable @NotNull Long id) throws Exception {
        return new ResponseEntity<Card>(cardService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(
            @PathVariable @NotNull Long id,
            @RequestBody Card card
    ) throws Exception {
        return new ResponseEntity<Card>(cardService.updateCard(card), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(
            @PathVariable @NotNull Long id
    ) throws Exception {
        cardService.deleteCard(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}/option/{idOption}")
    public ResponseEntity<Card> addNewOption(
            @PathVariable @NotNull Long id,
            @PathVariable @NotNull Long idOption
    ) throws Exception {
        Option op = optionService.findById(idOption);
        Card card = cardService.findById(id);
        return new ResponseEntity<Card>(cardService.addNewOption(card, op), HttpStatus.CREATED);
    }
}
