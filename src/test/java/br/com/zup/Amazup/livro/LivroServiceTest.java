package br.com.zup.Amazup.livro;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class LivroServiceTest {

    @MockBean
    private LivroRepository livroRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private LivroService livroService;

}
