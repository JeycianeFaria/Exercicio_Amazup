package br.com.zup.Amazup.autor.dtos;

import javax.validation.constraints.NotNull;

public class AutorDTO {

    @NotNull(message = "Id obrigatório")
    private int id;


    public AutorDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
