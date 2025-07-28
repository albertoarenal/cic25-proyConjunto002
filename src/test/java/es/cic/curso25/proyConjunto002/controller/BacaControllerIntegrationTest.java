package es.cic.curso25.proyConjunto002.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Baca;
import es.cic.curso25.proyConjunto002.model.Coche;
import es.cic.curso25.proyConjunto002.repository.CocheRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class BacaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        ;
        baca.setLargo(2.5);
        ;

        String bacaJson = objectMapper.writeValueAsString(baca);

        mockMvc.perform(post("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Baca registroCreado = objectMapper.readValue(respuesta, Baca.class);
                    assertTrue(registroCreado.getId() > 0, "El valor debe ser mayor que 0");

                });
    }

    @Test
    void testCreateException() throws Exception {

        Baca baca = new Baca();
        baca.setId(2L);
        baca.setAncho(1.5);
        baca.setPeso(30);

        baca.setLargo(2.5);

        String bacaJson = objectMapper.writeValueAsString(baca);

        mockMvc.perform(post("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testDelete() throws Exception {

        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        String bacaJson = objectMapper.writeValueAsString(baca);

        MvcResult mvcResult = mockMvc.perform(post("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaJson))
                .andExpect(status().isOk())
                .andReturn();

        Baca bacaGuardada = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Baca.class);

        Long id = bacaGuardada.getId();

        mockMvc.perform(delete("/api/vehiculo/coche/baca/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCoche() throws Exception {

        Baca baca = new Baca();
        baca.setAncho(1.5);
        baca.setPeso(30);
        baca.setLargo(2.5);

        String bacaJson = objectMapper.writeValueAsString(baca);

        MvcResult mvcResult = mockMvc.perform(post("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaJson))
                .andExpect(status().isOk())
                .andReturn();

        Baca bacaGuardada = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Baca.class);

        Long id = bacaGuardada.getId();

        mockMvc.perform(get("/api/vehiculo/coche/baca/" + id)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Baca recibida = objectMapper.readValue(json, Baca.class);
                    assertEquals(1.5, recibida.getAncho());
                    assertEquals(2.5, recibida.getLargo());
                });
    }

   
}
