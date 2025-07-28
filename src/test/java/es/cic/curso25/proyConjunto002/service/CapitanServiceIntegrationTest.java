package es.cic.curso25.proyConjunto002.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyConjunto002.model.Capitan;

@SpringBootTest
public class CapitanServiceIntegrationTest {

    @Autowired
    private CapitanService capitanService;

    @Test
    void testGetOne() {
        Capitan capitan = new Capitan();
        capitan.setNombre("Sergio");
        capitan.setApellidos("Martínez Lagos");
        capitan.setNumeroDeLicencia("4567935O");
        capitanService.create(capitan);
        assertTrue(capitanService.getAll().getFirst() != null);
    }

    @Test
    void testCreate() {
        Capitan capitan = new Capitan();
        capitan.setNombre("Manuel");
        capitan.setApellidos("García Salas");
        capitan.setNumeroDeLicencia("3464325T");
        capitan = capitanService.create(capitan);
        Long idCapitan = capitan.getId();
        assertTrue(idCapitan > 0);
    }

   

    @Test
    void TestGetAll() {
        Capitan capitan1 = new Capitan();
        capitan1.setNombre("Pedro");
        capitan1.setApellidos("Lucero Reinosa");
        capitan1.setNumeroDeLicencia("2345678F");
        capitanService.create(capitan1);
        
        Capitan capitan2 = new Capitan();
        capitan2.setNombre("Mario");
        capitan2.setApellidos("Salazar Pedrosa");
        capitan2.setNumeroDeLicencia("9587463R");
        capitanService.create(capitan2);

        Capitan capitan3 = new Capitan();
        capitan3.setNombre("Miguel");
        capitan3.setApellidos("Niceas Temprano");
        capitan3.setNumeroDeLicencia("9685432P");
        capitanService.create(capitan3);

        List<Capitan> listaCapitan = capitanService.getAll();

        assertEquals(listaCapitan.size(), 3);

    }


    @Test
    void testUpdate() {
        Capitan capitan1 = new Capitan();
        capitan1.setNombre("Pedro");
        capitan1.setApellidos("Lucero Reinosa");
        capitan1.setNumeroDeLicencia("2345679F");
        capitanService.create(capitan1);
        capitan1.setNombre("Florencio");
        capitanService.update(capitan1);
        assertEquals("Florencio", capitan1.getNombre());

    }



    @Test
    void testDelete() {
        Capitan capitan1 = new Capitan();
        capitan1.setNombre("Humberto");
        capitan1.setApellidos("Hernández Blanco");
        capitan1.setNumeroDeLicencia("8465738F");

        Capitan capitanCreado = capitanService.create(capitan1);
        assertTrue(capitanCreado != null);

        capitanService.delete(capitanCreado.getId());

        Optional<Capitan> capitanEliminado = capitanService.get(capitanCreado.getId());
        assertTrue(capitanEliminado.isEmpty());

    }


}
