package br.com.zup.Amazup.livro.componentes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConversorModelMapper {

    @Bean
    private ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
