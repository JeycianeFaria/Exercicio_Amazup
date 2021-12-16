package br.com.zup.Amazup.autor;

import br.com.zup.Amazup.livro.componentes.ConstrutorURI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

}
