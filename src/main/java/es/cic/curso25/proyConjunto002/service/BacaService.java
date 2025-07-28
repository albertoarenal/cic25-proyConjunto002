package es.cic.curso25.proyConjunto002.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyConjunto002.model.Baca;
import es.cic.curso25.proyConjunto002.repository.BacaRepository;

@Service
@Transactional
public class BacaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BacaService.class);

    @Autowired
    private BacaRepository bacaRepository;

    @Transactional(readOnly = true)
    public Optional<Baca> get(Long id) {

        LOGGER.info(String.format("Leo la baca que tiene el €id", id));

        Optional<Baca> baca = bacaRepository.findById(id);

        return baca;

    }

    @Transactional(readOnly = true)
    public List<Baca> getAll() {

        LOGGER.info(String.format("Leo todas las bacas"));

        return bacaRepository.findAll();
    }

    public Baca create (Baca baca){
        LOGGER.info("Creo una baca nueva");

        bacaRepository.save(baca);

        return baca;
    }


    public Baca update(Baca baca){

        LOGGER.info("Creo que baca");

        bacaRepository.save(baca);

        return baca;

    }

    public void delete(Long id){

        LOGGER.info("Borro una baca");

        bacaRepository.deleteById(id);
    }
    
}
