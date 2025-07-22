package es.cic.curso25.proyConjunto002.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proyConjunto002.model.Barco;
import es.cic.curso25.proyConjunto002.repository.BarcoRepository;

@Service
public class BarcoService {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(BarcoService.class);

    @Autowired
    private BarcoRepository barcoRepository;

    public Barco create(Barco barco){
        barco=barcoRepository.save(barco);

        return barco;
    }

    public Optional <Barco> get(long id) {
        LOGGER.info("Consultando el barco con el id " + id);
        Optional<Barco> resultado = barcoRepository.findById(id);

        return resultado;
    }

    public List<Barco> getAll() {
        
        return barcoRepository.findAll();
    }

    public void update (Barco barco) {
        
        barcoRepository.save(barco);
    }

    public void delete (long id){
        LOGGER.info("Borrando un barco");

        barcoRepository.deleteById(id);

    }

}
