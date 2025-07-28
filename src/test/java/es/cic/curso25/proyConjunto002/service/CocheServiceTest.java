package es.cic.curso25.proyConjunto002.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.repository.CocheRepository;

@SpringBootTest
public class CocheServiceTest {

    @Autowired
    private CocheService cocheService;

    @Test
    void testCreate() {

        Coche coche = new Coche();

        coche.setMarca("Opel");
        coche.setMatricula("2234HBH");
        coche.setAnio(2022);

        cocheService.create(coche);

        long esperado = coche.getId();

        assertTrue(esperado > 0);

    }

    @Test
    void testDelete() {

        Coche coche = new Coche();

        coche.setMarca("Opel");
        coche.setMatricula("2234HBH");
        coche.setAnio(2022);

        Coche cocheGuardado = cocheService.create(coche);

        assertTrue(cocheGuardado != null);

        cocheService.delete(cocheGuardado.getId());

        Optional<Coche> eliminado = cocheService.get(cocheGuardado.getId());

        assertTrue(eliminado.isEmpty());

    }

    @Test
    void testGet() {

        Coche coche = new Coche();

        coche.setMarca("Opel");
        coche.setMatricula("2234HBH");
        coche.setAnio(2022);

        cocheService.create(coche);

        Optional<Coche> cocheBuscado = cocheService.get(1L);

        assertTrue(cocheBuscado != null);
    }

    @Test
    void testGetAll() {

        Coche coche = new Coche();

        coche.setMarca("Opel");
        coche.setMatricula("2234HBH");
        coche.setAnio(2022);

        cocheService.create(coche);

        Coche coche2 = new Coche();

        coche2.setMarca("Opel");
        coche2.setMatricula("2234HBH");
        coche2.setAnio(2022);

        cocheService.create(coche2);

        List<Coche> coches = cocheService.getAll();

        assertTrue(coches.size() > 1);

    }
}
