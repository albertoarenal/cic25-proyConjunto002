package es.cic.curso25.proyConjunto002.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proyConjunto002.model.Barco;

@SpringBootTest
public class BarcoServiceIntegrationTest {

    @Autowired
    private BarcoService barcoService;

    @Test
    void testCreate() {
        Barco barco = new Barco();
        barco.setTipoBarco("Crucero");
        barco.setAnio(2009);
        barco.setMaterial("Acero");
        barco.setCapacidad(455);
        barco = barcoService.create(barco);
        Long idBarco = barco.getId();
        assertTrue(idBarco > 0);
    }

    @Test
    void testGetOne() {
        Barco barco = new Barco();
        barco.setTipoBarco("Crucero");
        barco.setAnio(2009);
        barco.setMaterial("Acero");
        barco.setCapacidad(455);
        barcoService.create(barco);
        assertEquals("Crucero", barcoService.getAll().get(0).getTipoBarco());
        
    }

    @Test
    void TestGetAll() {
        Barco barco1 = new Barco();
        barco1.setTipoBarco("Crucero");
        barco1.setAnio(2009);
        barco1.setMaterial("Acero");
        barco1.setCapacidad(289);
        barcoService.create(barco1);

        Barco barco2 = new Barco();
        barco2.setTipoBarco("Lancha");
        barco2.setAnio(2014);
        barco2.setMaterial("Acero");
        barco2.setCapacidad(4);
        barcoService.create(barco2);

        Barco barco3 = new Barco();
        barco3.setTipoBarco("Yate");
        barco3.setAnio(2007);
        barco3.setMaterial("Acero");
        barco3.setCapacidad(25);
        barcoService.create(barco3);

        List<Barco> listaBarco = barcoService.getAll();

        assertEquals(listaBarco.size(), 3);

    }


    @Test
    void testUpdate() {
        Barco barco = new Barco();
        barco.setTipoBarco("Ferry");
        barco.setAnio(1979);
        barco.setMaterial("Acero");
        barco.setCapacidad(682);
        barco = barcoService.create(barco);
        barco.setTipoBarco("Crucero");
        barco.setAnio(1992);
        barco.setMaterial("Acero");
        barco.setCapacidad(270);
        barcoService.update(barco);
        assertEquals(1992, barco.getAnio());
        assertEquals("Crucero", barco.getTipoBarco());

    }



    @Test
    void testDelete() {
        Barco barco = new Barco();
        barco.setTipoBarco("Crucero");
        barco.setAnio(1989);
        barco.setMaterial("Fibra de carbono");
        
        Barco barcoCreado = barcoService.create(barco);
        assertTrue(barcoCreado != null);

        barcoService.delete(barcoCreado.getId());
        
        Optional<Barco> barcoEliminado = barcoService.get(barcoCreado.getId());
        assertTrue(barcoEliminado.isEmpty());


    }


}
