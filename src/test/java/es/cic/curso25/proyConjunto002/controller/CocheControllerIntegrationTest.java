package es.cic.curso25.proyConjunto002.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.repository.CocheRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class CocheControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CocheRepository cocheRepository;

    @AfterEach
    void limpiarBaseDeDatos() {
        cocheRepository.deleteAll();
    }

    @Test
    void testCreate() throws Exception {
        Coche coche = new Coche();
        coche.setMarca("Opel");
        coche.setMatricula("2336BKB");
        coche.setAnio(2020);

        String cocheJson = objectMapper.writeValueAsString(coche);

        mockMvc.perform(post("/api/vehiculo/coche")
                .contentType("application/json")
                .content(cocheJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Coche registroCreado = objectMapper.readValue(respuesta, Coche.class);
                    assertTrue(registroCreado.getId() > 0, "El valor debe ser mayor que 0");

                    Optional<Coche> registroRealmenteCreado = cocheRepository
                            .findById(registroCreado.getId());

                    assertTrue(registroRealmenteCreado.isPresent());

                });
    }

    @Test
    void testDelete() throws Exception {

        Coche coche = new Coche();
        coche.setMarca("Opel");
        coche.setMatricula("2336BKB");
        coche.setAnio(2020);

        coche = cocheRepository.save(coche);

        Long id = coche.getId();

       
        mockMvc.perform(delete("/api/vehiculo/coche/" + id))
                .andExpect(status().isOk());

        Optional<Coche> eliminado = cocheRepository.findById(id);
        assertTrue(eliminado.isEmpty()); 
    }

    @Test
    void testGetCoche() throws Exception {

        Coche coche = new Coche();
        coche.setMarca("Opel");
        coche.setMatricula("3626BGV");
        coche.setAnio(2020);

     
        coche = cocheRepository.save(coche);

        String habitoJson = objectMapper.writeValueAsString(coche);

      
        mockMvc.perform(get("/api/vehiculo/coche/" + coche.getId())
                .contentType("application/json"))
               
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Coche recibido = objectMapper.readValue(json, Coche.class);
                    assertEquals("Opel", recibido.getMarca());
                    assertEquals("3626BGV", recibido.getMatricula());
                });
    }

    @Test
    void testUpdate() throws Exception {

          Coche coche = new Coche();
        coche.setMarca("Opel");
        coche.setMatricula("2336BKB");
        coche.setAnio(2020);

        String cocheJson = objectMapper.writeValueAsString(coche);

        mockMvc.perform(post("/api/vehiculo/coche")
                .contentType("application/json")
                .content(cocheJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Coche registroCreado = objectMapper.readValue(respuesta, Coche.class);
                    assertTrue(registroCreado.getId() > 0, "El valor debe ser mayor que 0");

                    Optional<Coche> registroRealmenteCreado = cocheRepository
                            .findById(registroCreado.getId());

                    assertTrue(registroRealmenteCreado.isPresent());

                });
    }
}
