package br.com.zup.Amazup.livro.dtos;

import java.net.URI;

public class RetornoCadastroDTO {

    private URI vitrine;


    public RetornoCadastroDTO() {
    }


    public URI getVitrine() {
        return vitrine;
    }

    public void setVitrine(URI vitrine) {
        this.vitrine = vitrine;
    }
}
