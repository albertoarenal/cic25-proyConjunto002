package es.cic.curso25.proyConjunto002.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.repository.CocheRepository;

@Service
public class CocheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocheService.class);

    @Autowired
    private CocheRepository cocheRepository;

    public List<Coche> getAll() {

        List<Coche> coches = cocheRepository.findAll();

        return coches;
    }

    public Optional<Coche> get(Long id) {

        Optional<Coche> coche = cocheRepository.findById(id);

        return coche;
    }

    public Coche create(Coche coche) {

        cocheRepository.save(coche);

        return coche;

    }

    public Long delete(Long id) {

        cocheRepository.deleteById(id);

        return id;
    }

    public Coche update(Coche coche) {

       coche = cocheRepository.save(coche);

        return coche;
    }

}
