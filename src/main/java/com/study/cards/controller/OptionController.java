package com.study.cards.controller;

import com.study.cards.entity.Option;
import com.study.cards.service.OptionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @PostMapping
    public ResponseEntity<Option> newOption(@RequestBody Option op) throws ParseException {
            return new ResponseEntity<>(optionService.saveOption(op), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Option>> getAll() {
        return new ResponseEntity<>(optionService.getAllOptions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOption(@PathVariable @NotNull Long id) throws Exception {
        return new ResponseEntity<Option>(optionService.findById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Option> updateFilm(
            @PathVariable @NotNull Long id,
            @RequestBody Option op
    ) throws Exception {
            return new ResponseEntity<Option>(optionService.updateOption(id, op), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(
           @PathVariable @NotNull Long id
    ) throws Exception {
            optionService.deleteOption(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
