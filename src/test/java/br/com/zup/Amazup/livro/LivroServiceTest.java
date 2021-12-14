package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static br.com.zup.Amazup.livro.enuns.Genero.FICCAO_CIENTIFICA;
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
    public void  setup(){
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

}
