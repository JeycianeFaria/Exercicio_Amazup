package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.livro.componentes.ConstrutorURI;
import br.com.zup.Amazup.livro.componentes.ConversorModelMapper;
import br.com.zup.Amazup.livro.dtos.CadastroLivroDTO;
import br.com.zup.Amazup.livro.dtos.RetornoCadastroDTO;
import br.com.zup.Amazup.livro.exceptions.LivroNaoEncontradoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.zup.Amazup.livro.enuns.Genero.FICCAO_CIENTIFICA;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({LivroController.class, ConversorModelMapper.class, ConstrutorURI.class})
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
    private void setup() {
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
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vitrine")
                        .value("http://localhost:8080/livros/" + livro.getId())
                );

        String jsonResposta = respostaRequisicao.andReturn().getResponse().getContentAsString();
        RetornoCadastroDTO retornoCadastroDTO = objectMapper.readValue(jsonResposta, RetornoCadastroDTO.class);

    }

    @Test
    public void testarCadastrarLivroValidacaoNomeEmBranco() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        livroCadastro.setNome("");
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarCadastrarLivroValidacaoNomeNotNull() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        livroCadastro.setNome(null);
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarCadastrarLivroValidacaoAutorNotNull() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        livroCadastro.setAutor(null);
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarCadastrarLivroValidacaoGenero() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        String json = objectMapper.writeValueAsString(livroCadastro);
        json = json.replace("\"genero\":\"FICCAO_CIENTIFICA\"}", "\"genero\":\"Teste\"}");

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarCadastrarLivroValidacaoGeneroNotNull() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        livroCadastro.setGenero(null);
        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarCadastrarLivroValidacaoPrecoNegativo() throws Exception {
        when(livroService.salvarLivro(any(Livro.class))).thenReturn(livro);
        livroCadastro.setPreco(-1);

        String json = objectMapper.writeValueAsString(livroCadastro);

        ResultActions respostaRequisicao = mockMvc.perform(post("/livros")
                        .contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isUnprocessableEntity());

        verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarRotaParaExibirLivro() throws Exception {
        when(livroService.buscarLivroPorId(anyInt())).thenReturn(livro);

        ResultActions respostaRequisicao = mockMvc.perform(get("/livros/" + livro.getId())
                .contentType(APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void testarRotaParaExibirLivroNaoEncontrado() throws Exception {
        doThrow(new LivroNaoEncontradoException()).when(livroService).buscarLivroPorId(anyInt());

        ResultActions respostaRequisicao = mockMvc.perform(get("/livros/" + livro.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
