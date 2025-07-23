package es.cic.curso25.proyConjunto002.controller;

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

import es.cic.curso25.proyConjunto002.model.Capitan;
import es.cic.curso25.proyConjunto002.service.CapitanService;

@RestController
@RequestMapping("/api/vehiculo/capitan")
public class CapitanController {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CapitanController.class);

    @Autowired
    private CapitanService capitanService;

    //Read
    @GetMapping
    public List<Capitan> get() {
        return capitanService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Capitan> get(@PathVariable long id){
        LOGGER.info("Consultando el capitan con id " + id);
        Optional<Capitan> capitan = capitanService.get(id);

        return capitan;
    }

    //Create
    @PostMapping
    public Capitan create(@RequestBody Capitan capitan){
        LOGGER.info("Creando un capitán nuevo " + capitan);
        Capitan capitanNuevo = capitanService.create(capitan);

        return capitanNuevo;
    }

    //Update
    @PutMapping
    public void update(@RequestBody Capitan capitan){
        capitanService.update(capitan);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        capitanService.delete(id);
    }

}
