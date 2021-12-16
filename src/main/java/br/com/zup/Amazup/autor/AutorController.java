package br.com.zup.Amazup.autor;

import br.com.zup.Amazup.autor.dtos.AutorCadastroDto;
import br.com.zup.Amazup.autor.dtos.AutorUriDto;
import br.com.zup.Amazup.livro.componentes.ConstrutorURI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConstrutorURI uri;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorUriDto cadastrarAutor(@RequestBody AutorCadastroDto autorDto) {
        Autor autor = autorService.salvarAutor(modelMapper.map(autorDto, Autor.class));

        AutorUriDto autorUriDto = modelMapper.map(autor, AutorUriDto.class);
        autorUriDto.setUri(uri.criarUri("/autores" + autor.getId()));

        return  autorUriDto;
    }

}
