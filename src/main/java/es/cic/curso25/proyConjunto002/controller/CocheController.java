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

import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.service.CocheService;

@RestController
@RequestMapping("/api/vehiculo/coche")
public class CocheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocheController.class);

    @Autowired
    private CocheService cocheService;

    @GetMapping("")
    public List<Coche> getAll() {

        LOGGER.info("Endpoint GET /api/vehiculo/coche con todos los coches");

        List<Coche> coches = cocheService.getAll();

        return coches;
    }

    @GetMapping("/{id}")
    public Optional<Coche> get(@PathVariable Long id) {

        LOGGER.info("Endpoint GET /api/vehiculo/coche/{id} que devuelve el coche buscado por id");

        Optional<Coche> coche = cocheService.get(id);

        return coche;
    }

    @PostMapping
    public Coche create(@RequestBody Coche coche) {

        if (coche.getId() != null) {
            throw new ModificacionSecurityException("No puedes pasar un id distinto de 0");

        }

        LOGGER.info("Endpoint POST /api/vehiculo/coche para guardar un coche enviado en el body");
        cocheService.create(coche);

        return coche;
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {

        LOGGER.info("Endpoint DELETE /api/vehiculo/coche/{id} para eliminar el coche buscado por id");

        cocheService.delete(id);

        return id;
    }

    @PutMapping
    public Coche update(@RequestBody Coche coche) {


         if (coche.getId() == null) {
            throw new CreacionSecurityException("El id no puede ser nulo al actualizar");
        }

        LOGGER.info("Actualizando el coche");
        coche = cocheService.update(coche);

        return coche;

    }

}
