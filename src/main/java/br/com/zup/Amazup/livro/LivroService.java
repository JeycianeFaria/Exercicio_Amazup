package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.livro.exceptions.LivroJaCadastradoException;
import br.com.zup.Amazup.livro.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;


    public Livro salvarLivro(Livro livro){
        if (livroRepository.existsByNomeAndAutorId(livro.getNome(),livro.getAutor().getId())){
            throw new LivroJaCadastradoException("Livro já cadastrado");
        }

        return livroRepository.save(livro);
    }

    public Livro buscarLivroPorId(int id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty()){
            throw new LivroNaoEncontradoException();
        }

        return livroOptional.get();
    }

}
