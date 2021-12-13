package br.com.zup.Amazup.livro.dtos;

import br.com.zup.Amazup.autor.dtos.AutorDTO;
import br.com.zup.Amazup.livro.enuns.Genero;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastroLivroDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Valid
    private AutorDTO autor;
    @Min(value = 1, message = "O valor é obrigatório e deve ser maior ou igual a 1.00")
    private double preco;
    @NotNull(message = "Gênero é obrigatório")
    private Genero genero;


    public CadastroLivroDTO() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

}
