package es.cic.curso25.proyConjunto002.controller;

import es.cic.curso25.proyConjunto002.model.Baca;
import es.cic.curso25.proyConjunto002.service.BacaService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculo/coche/baca")
public class BacaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BacaController.class);

    @Autowired
    private BacaService bacaService;

    @GetMapping("/{id}")
    public Optional<Baca> get(@PathVariable Long id) {

        LOGGER.info(String.format("Leo la baca con id ", id));

        Optional<Baca> baca = bacaService.get(id);

        return baca;

    }

    @GetMapping
    public List<Baca> get() {
        LOGGER.info("Leo la lista completa con las bacas");

        return bacaService.get();
    }

    @PostMapping
    public Baca create(@RequestBody Baca baca){

        if (baca.getId()!=null){
            throw new ModificacionSecurityException("El id que has pasado no es válido");
        }

        LOGGER.info("Creo una baca");

        bacaService.create(baca);

        return baca;
    }

    @PutMapping
    public void update(@RequestBody Baca baca) {

        if (baca.getId() == null ){
            throw new CreacionSecurityException("No se puede crear una baca sin un id");
        }
        LOGGER.info("Actualizo una baca");

        bacaService.update(baca);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {

        LOGGER.info("Borro una baca");

        bacaService.delete(id);
    }

}
