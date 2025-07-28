package es.cic.curso25.proyConjunto002.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyConjunto002.model.Baca;

@SpringBootTest
public class BacaServiceTest {

    @Autowired
    private BacaService bacaService;

    @Test
    void testCreate() {

        Baca baca = new Baca();

        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        bacaService.create(baca);

        Long esperado = baca.getId();

        assertTrue(esperado > 0);

    }

    @Test
    void testDelete() {

        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        Baca bacaGuardada = bacaService.create(baca);

        bacaService.delete(bacaGuardada.getId());

        Optional<Baca> eliminado = bacaService.get(bacaGuardada.getId());

        assertTrue(eliminado.isEmpty());
    }

    @Test
    void testGet() {

        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        Baca bacaGuardada = bacaService.create(baca);

        Optional<Baca> bacaBuscada = bacaService.get(bacaGuardada.getId());

        assertTrue(bacaBuscada.isPresent());
        assertEquals(bacaGuardada.getId(), bacaBuscada.get().getId());
    }

    @Test
    void testGetAll() {

        Baca baca1 = new Baca();
        baca1.setAncho(1.5);
        baca1.setPeso(30);
        baca1.setLargo(2.5);

        Baca baca2 = new Baca();
        baca2.setAncho(2.0);
        baca2.setPeso(40);
        baca2.setLargo(3.0);

        bacaService.create(baca1);
        bacaService.create(baca2);

        List<Baca> lista = bacaService.getAll();

        assertTrue(lista.size() >= 2);
    }

    @Test
    void testUpdateBaca() {

        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        Baca bacaGuardada = bacaService.create(baca);

        bacaGuardada.setPeso(35);
        bacaGuardada.setLargo(3.0);

        Baca bacaActualizada = bacaService.update(bacaGuardada);

        assertEquals(35, bacaActualizada.getPeso());
        assertEquals(3.0, bacaActualizada.getLargo());
        assertEquals(bacaGuardada.getId(), bacaActualizada.getId());
    }

}
