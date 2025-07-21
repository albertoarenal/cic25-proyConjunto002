package es.cic.curso25.proyConjunto002.controller;

import java.util.List;

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

import es.cic.curso25.proyConjunto002.model.Barco;
import es.cic.curso25.proyConjunto002.service.BarcoService;

@RestController
@RequestMapping("/api/vehiculo/barco")
public class BarcoController {

    //Logger
    private final static Logger LOGGER = LoggerFactory.getLogger(BarcoController.class);

    @Autowired
    private BarcoService barcoService;

    @GetMapping
    public List<Barco> get() {
        return barcoService.get();
    }

    @GetMapping("/{id}")
    public Barco get(@PathVariable long id){
        LOGGER.info("Consultando el barco con id: " + id);
        Barco barco = barcoService.get(id);

        return barco;
    }

    @PostMapping
    public Barco create(@RequestBody Barco barco) {
        Barco barcoNuevo = barcoService.create(barco);

        return barcoNuevo;
    }

    @PutMapping
    public void update(@RequestBody Barco barco){
        barcoService.update(barco);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        barcoService.delete(id);
    }


}
