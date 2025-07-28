package es.cic.curso25.proyConjunto002.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proyConjunto002.model.Capitan;
import es.cic.curso25.proyConjunto002.repository.CapitanRepository;

@Service
@Transactional
public class CapitanService {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CapitanService.class);

    @Autowired
    private CapitanRepository capitanRepository;

    public Capitan create(Capitan capitan){
        capitan = capitanRepository.save(capitan);

        return capitan;
    }

    @Transactional(readOnly = true)
    public Optional <Capitan> get(long id) {
        Optional<Capitan> resultado = capitanRepository.findById(id);

        return resultado;
    }

    @Transactional(readOnly = true)
    public List<Capitan> getAll() {
        
        return capitanRepository.findAll();
    }

    public void update (Capitan capitan) {
        
        capitanRepository.save(capitan);
    }

    public void delete (long id){
        LOGGER.info("Borrando un capitán");
        capitanRepository.deleteById(id);

    }

}

