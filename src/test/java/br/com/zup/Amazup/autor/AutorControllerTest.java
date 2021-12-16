package br.com.zup.Amazup.autor;

import br.com.zup.Amazup.autor.dtos.AutorCadastroDto;
import br.com.zup.Amazup.autor.dtos.AutorUriDto;
import br.com.zup.Amazup.livro.componentes.ConstrutorURI;
import br.com.zup.Amazup.livro.componentes.ConversorModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({AutorController.class, ConversorModelMapper.class,ConstrutorURI.class})
public class AutorControllerTest {

    @MockBean
    private AutorService autorService;

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper;
    private Autor autor;
    private AutorCadastroDto autorDto;


    @BeforeEach
    private void setup(){
        objectMapper = new ObjectMapper();
        autor = new Autor();
        autorDto = new AutorCadastroDto();


        autor.setId(1);
        autor.setNome("Douglas Adams");

        autorDto.setNome("Douglas Adams");

    }


    @Test
    public void testarRotaParaCadastrarAutor() throws Exception {
        when(autorService.salvarAutor(any(Autor.class))).thenReturn(autor);
        String json = objectMapper.writeValueAsString(autorDto);

        ResultActions respostaDaRequisicao = mockMvc.perform(post("/autores")
                .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());

        String jsonResposta = respostaDaRequisicao.andReturn().getResponse().getContentAsString();
        AutorUriDto retornoCadastroDTO = objectMapper.readValue(jsonResposta, AutorUriDto.class);

    }

}
