package es.cic.curso25.proyConjunto002.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.repository.CocheRepository;

@Service
@Transactional
public class CocheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocheService.class);

    @Autowired
    private CocheRepository cocheRepository;

    @Transactional(readOnly = true)
    public List<Coche> getAll() {

        LOGGER.info("Devolviendo una lista con todos los coches");

        List<Coche> coches = cocheRepository.findAll();

        return coches;
    }

    
    @Transactional(readOnly = true)
    public Optional<Coche> get(Long id) {

        LOGGER.info(String.format("Devolviendo el coche con id %s", id));
        Optional<Coche> coche = cocheRepository.findById(id);

        return coche;
    }

    public Coche create(Coche coche) {

        LOGGER.info(String.format("Creando el coche con id %s", coche.getId()));
        cocheRepository.save(coche);

        return coche;

    }

    public Long delete(Long id) {

        LOGGER.info(String.format("Eliminando el coche con id %s", id));

        cocheRepository.deleteById(id);

        return id;
    }

    public Coche update(Coche coche) {

        LOGGER.info(String.format("Actualizando el coche con id %s", coche.getId()));
        coche = cocheRepository.save(coche);

        return coche;
    }

}
