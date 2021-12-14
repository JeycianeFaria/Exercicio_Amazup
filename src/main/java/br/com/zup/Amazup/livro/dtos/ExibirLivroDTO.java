package br.com.zup.Amazup.livro.dtos;

import br.com.zup.Amazup.autor.dtos.AutorUriDto;
import br.com.zup.Amazup.livro.enuns.Genero;


public class ExibirLivroDTO {

    private String nome;
    private AutorUriDto autor;
    private double preco;
    private Genero genero;

    public ExibirLivroDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AutorUriDto getAutor() {
        return autor;
    }

    public void setAutor(AutorUriDto autor) {
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
