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

import es.cic.curso25.proyConjunto002.model.Capitan;
import es.cic.curso25.proyConjunto002.repository.CapitanRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CapitanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CapitanRepository capitanRepository;


    //Test Create - POST
    @Test
    void testCreate() throws Exception{

        Capitan capitan = new Capitan();
        capitan.setNombre("Jack");
        capitan.setApellidos("Sparrow");
        capitan.setNumeroDeLicencia("1234567A");
   

        String capitanJson = objectMapper.writeValueAsString(capitan);

        mockMvc.perform(post("/api/capitan")
            .contentType("application/json")
            .content(capitanJson))
            .andExpect(status().isOk())
            .andExpect(result -> {
                String respuesta = result.getResponse().getContentAsString();
                                Capitan registroCreado = objectMapper.readValue(respuesta, Capitan.class);
                                assertTrue(registroCreado.getId() > 0, "El valor debe ser mayor que 0");

                                Optional<Capitan> registroRealmenteCreado = capitanRepository.findById(registroCreado.getId());
                                assertTrue(registroRealmenteCreado.isPresent());
            });

    }
    
    //Test GET 
    @Test
    void testGet() throws Exception {

            Capitan capitan = new Capitan();
            capitan.setNombre("Jack");
            capitan.setApellidos("Sparrow");
            capitan.setNumeroDeLicencia("1234567B");

            String capitanJson = objectMapper.writeValueAsString(capitan);

            mockMvc.perform(post("/api/capitan")
            .contentType("application/json")
            .content(capitanJson))
            .andExpect(status().isOk());

            mockMvc.perform(get("/api/capitan/1"))
                .andExpect(status().isOk());
        }


    //Test DELETE
    @Test
    void testDelete() throws Exception {


            Capitan capitan = new Capitan();
            capitan.setNombre("Jack");
            capitan.setApellidos("Sparrow");
            capitan.setNumeroDeLicencia("1234567C");


            String capitanJson = objectMapper.writeValueAsString(capitan);

            mockMvc.perform(post("/api/capitan")
                .contentType("application/json")
                .content(capitanJson))
                .andExpect(status().isOk());

            mockMvc.perform(get("/api/capitan/1"))
                .andExpect(status().isOk());

            mockMvc.perform(delete("/api/capitan/1"))
                .andExpect(status().isOk())
                .andReturn();
         }
}

