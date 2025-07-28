package es.cic.curso25.proyConjunto002.uc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import es.cic.curso25.proyConjunto002.model.Baca;
import es.cic.curso25.proyConjunto002.model.Coche;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UnionBacaACocheTest {

     @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearLeerActualizarEliminarBaca() throws Exception {
       
        Coche coche = new Coche();
        coche.setMarca("Toyota");
        coche.setMatricula("BKB2456");
        coche.setAnio(2020);

        String cocheJson = objectMapper.writeValueAsString(coche);

        MvcResult cocheResult = mockMvc.perform(post("/api/vehiculo/coche")
                .contentType("application/json")
                .content(cocheJson))
                .andExpect(status().isOk())
                .andReturn();

        Coche cocheCreado = objectMapper.readValue(
                cocheResult.getResponse().getContentAsString(), Coche.class);

        assertNotNull(cocheCreado.getId());

    
        Baca baca = new Baca();
        baca.setAncho(1.2);
        baca.setLargo(2.0);
        baca.setPeso(25);
        baca.setCoche(cocheCreado); 

        String bacaJson = objectMapper.writeValueAsString(baca);

        MvcResult bacaResult = mockMvc.perform(post("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaJson))
                .andExpect(status().isOk())
                .andReturn();

        Baca bacaCreada = objectMapper.readValue(bacaResult.getResponse().getContentAsString(), Baca.class);

        assertNotNull(bacaCreada.getId());

        Long bacaId = bacaCreada.getId();
   
        mockMvc.perform(get("/api/vehiculo/coche/baca/" + bacaId))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Baca obtenida = objectMapper.readValue(result.getResponse().getContentAsString(), Baca.class);
                    assertEquals(bacaId, obtenida.getId());
                });

       
        bacaCreada.setAncho(1.8);
        String bacaActualizadaJson = objectMapper.writeValueAsString(bacaCreada);

        mockMvc.perform(put("/api/vehiculo/coche/baca")
                .contentType("application/json")
                .content(bacaActualizadaJson))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/vehiculo/coche/baca/" + bacaId))
                .andExpect(status().isOk());
    }
}
