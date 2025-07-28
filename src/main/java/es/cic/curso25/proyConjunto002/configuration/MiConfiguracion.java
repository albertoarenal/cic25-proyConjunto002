package es.cic.curso25.proyConjunto002.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mifichero.properties")
public class MiConfiguracion {

    @Value("${es.cic.curso25.proyConjunto002.adjudicacion.activada}")
    private boolean adjudicacionActivada;

    @Bean
    public MiClaseParametros miClaseParametros(){
        return new MiClaseParametros();
    }

    
}
