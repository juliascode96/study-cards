package com.study.cards.service;

import com.study.cards.entity.Option;
import com.study.cards.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    OptionRepository optionRepository;

    public Option saveOption(Option option){
        return optionRepository.save(option);
    }

    public List<Option> getAllOptions(){
        return optionRepository.findAll();
    }

    public Option findById(Long id) throws Exception {
        Option op = optionRepository.findById(id)
                .orElseThrow(() -> new ServerException("Entidad no encontrada"));

        return op;
    }

    public void deleteOption(Long id) throws Exception {
        if(optionRepository.existsById(id)){
            optionRepository.deleteById(id);
            return;
        }

        throw new ServerException("Opción no encontrada");
    }

    public Option updateOption(Long id, Option op) throws Exception {
        if(optionRepository.existsById(op.getId())){
            return optionRepository.save(op);
        }

        throw new ServerException("Opción no encontrada");
    }
}
