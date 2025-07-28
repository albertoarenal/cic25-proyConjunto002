package es.cic.curso25.proyConjunto002.uc;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proyConjunto002.model.Barco;
import es.cic.curso25.proyConjunto002.model.Capitan;

@SpringBootTest
@AutoConfigureMockMvc
public class AsignarCapitanABarcoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAsignarCapitan() throws Exception {

        Capitan capitan = new Capitan();
        capitan.setNombre("José");
        capitan.setApellidos("Gómez Pettinato");
        capitan.setNumeroDeLicencia("8574386U");

        String capitanACrearJson = objectMapper.writeValueAsString(capitan);
        
        MvcResult capitanMvcResult = mockMvc.perform(post("/api/capitan")
            .contentType("application/json")
            .content(capitanACrearJson))
            .andExpect(status().isOk())
            .andReturn();

        Capitan capitanCreado = objectMapper.readValue(
            capitanMvcResult.getResponse().getContentAsString(), Capitan.class);

        assertNotNull(capitanCreado.getId());

        Barco barcoTest = new Barco();
        barcoTest.setTipoBarco("Yate");
        barcoTest.setAnio(2017);
        barcoTest.setMaterial("Fibra de carbono");
        barcoTest.setCapacidad(9);

        barcoTest.setCapitan(capitanCreado);
        //capitan.setBarco(barcoTest);


        //Convertimos el objeto de tipo asignar en Json con ObjectMapper
        String barcoACrearJson = objectMapper.writeValueAsString(barcoTest);

        MvcResult mvcResult = mockMvc.perform(post("/api/capitan/asignacion")
        .contentType("application/json")
        .content(barcoACrearJson))
        .andExpect(status().isOk())
        .andExpect( capitanResult -> {
            assertNotNull(
                objectMapper.readValue(
                    capitanResult.getResponse().getContentAsString(), Barco.class),
                    "El capitan fue asignado al barco");
        })
        .andReturn();

        Barco barcoCreado = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Barco.class);
        Long id = barcoCreado.getId();

        mockMvc.perform(get("/api/vehiculo/barco/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(objectMapper.readValue(result.getResponse().getContentAsString(), Barco.class).getId(),
                            id);
                });   
         
                
        barcoCreado.getCapitan().setApellidos("Sanchez");


        String barcoJson = objectMapper.writeValueAsString(barcoCreado);

        mockMvc.perform(put("/api/vehiculo/barco")
                .contentType("application/json")
                .content(barcoJson))
                .andDo(print())                
                .andExpect(status().isOk());





        mockMvc.perform(delete("/api/vehiculo/barco/" + id))
                .andDo(print())        
                .andExpect(status().isOk());  
    }
}
