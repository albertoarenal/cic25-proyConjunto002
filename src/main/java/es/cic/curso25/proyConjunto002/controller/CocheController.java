package es.cic.curso25.proyConjunto002.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


     @GetMapping()
     public List<Coche> getAll(){

       List<Coche> coches= cocheService.getAll();

       return coches;
     }


     @GetMapping("/{id}")
     public Long get(@PathVariable Long id){


        throw new UnsupportedOperationException("Metodo sin implementar");
     }

     @PostMapping()
     public Coche create(@RequestBody Coche coche){
      
        
        throw new UnsupportedOperationException("Metodo sin implementar");
     }

     @DeleteMapping("/{id}")
     public Long delete(@PathVariable Long id){

         throw new UnsupportedOperationException("Metodo sin implementar");
     }


}
