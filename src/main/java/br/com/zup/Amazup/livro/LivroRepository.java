package br.com.zup.Amazup.livro;

import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro,Integer> {

    Boolean existsByNomeAndAutorId(String nome,Integer idAutor);

}
