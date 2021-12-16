package br.com.zup.Amazup.autor;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AutorServiceTest {

    @MockBean
    private AutorRepository autorRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private AutorService autorService;


    private Autor autor;


    @BeforeEach
    private void setup(){
        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");
    }


    @Test
    public void testarSalvarAutorCaminhoPositivo() {
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor autorRetorno = autorService.salvarAutor(autor);

        assertEquals(autor,autorRetorno);

        verify(autorRepository, times(1)).save(any(Autor.class));

    }

}
