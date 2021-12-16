package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.livro.exceptions.LivroJaCadastradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static br.com.zup.Amazup.livro.enuns.Genero.FICCAO_CIENTIFICA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LivroServiceTest {

    @MockBean
    private LivroRepository livroRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private LivroService livroService;

    private Livro livro;
    private Autor autor;


    @BeforeEach
    private void  setup(){
        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");

        livro = new Livro();
        livro.setId(1);
        livro.setNome("Adeus e Obrigado Pelos Peixes");
        livro.setAutor(autor);
        livro.setPreco(39.90);
        livro.setGenero(FICCAO_CIENTIFICA);
    }

    @Test
    public void testarSalvarLivroCaminhoPositivo(){
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        when(livroRepository.existsByNomeAndAutorId(anyString(), anyInt())).thenReturn(false);

        Livro livroRetorno = livroService.salvarLivro(livro);

        assertEquals(livro,livroRetorno);

        verify(livroRepository, times(1)).save(any(Livro.class));

    }

    @Test
    public void testarSalvarLivroRepetido() {
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        when(livroRepository.existsByNomeAndAutorId(anyString(), anyInt())).thenReturn(true);

        RuntimeException exception = assertThrows(LivroJaCadastradoException.class,
                () ->{livroService.salvarLivro(livro);});

        verify(livroRepository, times(0)).save(any(Livro.class));

    }

}
