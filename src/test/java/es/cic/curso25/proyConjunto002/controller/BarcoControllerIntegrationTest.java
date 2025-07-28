package es.cic.curso25.proyConjunto002.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Barco;
import es.cic.curso25.proyConjunto002.repository.BarcoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BarcoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BarcoRepository barcoRepository;

    //Test Create - POST
    @Test
    void testCreate() throws Exception{
        Barco barco = new Barco();
        barco.setTipoBarco("Lancha");
        barco.setAnio(2017);
        barco.setMaterial("Fibra de carbono");
        barco.setCapacidad(6);

        String barcoJson = objectMapper.writeValueAsString(barco);

        mockMvc.perform(post("/api/vehiculo/barco")
            .contentType("application/json")
            .content(barcoJson))
            .andExpect(status().isOk())
            .andExpect(result -> {
                String respuesta = result.getResponse().getContentAsString();
                                Barco registroCreado = objectMapper.readValue(respuesta, Barco.class);
                                assertTrue(registroCreado.getId() > 0, "El valor debe ser mayor que 0");

                                Optional<Barco> registroRealmenteCreado = barcoRepository.findById(registroCreado.getId());
                                assertTrue(registroRealmenteCreado.isPresent());
            });

    }
    
    //Test GET 
    @Test
    void testGet() throws Exception {
            Barco barco = new Barco();
            barco.setTipoBarco("Yate");
            barco.setAnio(2012);
            barco.setMaterial("Fibra de vidrio");
            barco.setCapacidad(15);

            String barcoJson = objectMapper.writeValueAsString(barco);

            mockMvc.perform(post("/api/vehiculo/barco")
            .contentType("application/json")
            .content(barcoJson))
            .andExpect(status().isOk());

            mockMvc.perform(get("/api/vehiculo/barco/1"))
                .andExpect(status().isOk());
        }


    //Test DELETE
    @Test
    void testDelete() throws Exception {
            Barco barco = new Barco();
            barco.setTipoBarco("Crucero");
            barco.setAnio(2002);
            barco.setMaterial("Acero");

            String barcoJson = objectMapper.writeValueAsString(barco);

            mockMvc.perform(post("/api/vehiculo/barco")
                .contentType("application/json")
                .content(barcoJson))
                .andExpect(status().isOk());

            mockMvc.perform(get("/api/vehiculo/barco/1"))
                .andExpect(status().isOk());

            mockMvc.perform(delete("/api/vehiculo/barco/1"))
                .andExpect(status().isOk())
                .andReturn();
         }
}
