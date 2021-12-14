package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.livro.dtos.CadastroLivroDTO;
import br.com.zup.Amazup.livro.dtos.RetornoCadastroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetornoCadastroDTO cadastrarLivro(@RequestBody @Valid CadastroLivroDTO livroRecebido){
        Livro livro = livroService.salvarLivro(modelMapper.map(livroRecebido,Livro.class));
        String vitrine = "http://localhost:8080/livros/" + livro.getId();

        RetornoCadastroDTO retornoCadastroDTO = new RetornoCadastroDTO();
        retornoCadastroDTO.setVitrine(vitrine);

        return retornoCadastroDTO;
    }

}
