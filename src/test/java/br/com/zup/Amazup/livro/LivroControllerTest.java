package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.livro.componentes.ConversorModelMapper;
import br.com.zup.Amazup.livro.dtos.CadastroLivroDTO;
import br.com.zup.Amazup.livro.dtos.RetornoCadastroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.zup.Amazup.livro.enuns.Genero.FICCAO_CIENTIFICA;

@WebMvcTest({LivroController.class, ConversorModelMapper.class})
public class LivroControllerTest {

    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Livro livro;
    private CadastroLivroDTO livroCadastro;
    private Autor autor;


    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();

        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");

        livro = new Livro();
        livro.setId(1);
        livro.setNome("Adeus e Obrigado Pelos Peixes");
        livro.setAutor(autor);
        livro.setPreco(39.90);
        livro.setGenero(FICCAO_CIENTIFICA);

        livroCadastro = new CadastroLivroDTO();
        livroCadastro.setNome("Adeus e Obrigado Pelos Peixes");
        livroCadastro.setAutor(autor);
        livroCadastro.setPreco(39.90);
        livroCadastro.setGenero(FICCAO_CIENTIFICA);

    }

    @Test
    public void testarCadastrarLivro() throws Exception {
        Mockito.when(livroService.salvarLivro(Mockito.any(Livro.class))).thenReturn(livro);
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(MockMvcRequestBuilders.post("/livros")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vitrine")
                        .value("http://localhost:8080/livros/" + livro.getId())
                );

        String jsonResposta = respostaRequisicao.andReturn().getResponse().getContentAsString();
        RetornoCadastroDTO retornoCadastroDTO = objectMapper.readValue(jsonResposta,RetornoCadastroDTO.class);

    }

}
