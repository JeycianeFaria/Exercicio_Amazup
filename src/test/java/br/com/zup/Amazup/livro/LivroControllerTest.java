package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.livro.componentes.ConversorModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({LivroController.class, ConversorModelMapper.class})
public class LivroControllerTest {

    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mockMvc;

}
