package com.study.cards.service;

import com.study.cards.entity.Card;
import com.study.cards.entity.Option;
import com.study.cards.repository.CardRepository;
import com.study.cards.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OptionRepository optionRepository;

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public Card findById(Long id) throws Exception{
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ServerException("Entidad no encontrada"));
        return card;
    }

    public void deleteCard(Long id) throws Exception {
        if(cardRepository.existsById(id)){
            cardRepository.deleteById(id);
            return;
        }

        throw new ServerException("Tarjeta no encontrada");
    }

    public Card updateCard(Card card) throws Exception {
        if(cardRepository.existsById(card.getId())){
            return cardRepository.save(card);
        }

        throw new ServerException("Tarjeta no encontrada");
    }

    public Card addNewOption(Card card, Option op) throws Exception {
        if(cardRepository.existsById(card.getId())){
            card.getOptions().add(op);
            return cardRepository.save(card);
        }

        throw new ServerException("Ha ocurrido un error");
    }
}
