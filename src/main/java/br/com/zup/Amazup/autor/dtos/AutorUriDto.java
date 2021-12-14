package br.com.zup.Amazup.autor.dtos;

import java.net.URI;

public class AutorUriDto {

    private URI uri;
    private String nome;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
