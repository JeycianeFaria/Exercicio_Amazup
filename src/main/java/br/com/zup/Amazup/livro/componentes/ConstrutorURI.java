package br.com.zup.Amazup.livro.componentes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ConstrutorURI {

    @Value("${server.port}")
    private String porta;
    @Value("${host}")
    private String host;


    public URI criarUri(String path){
        return UriComponentsBuilder.newInstance()
                .scheme("http").host(host).port(porta)
                .path(path).build().toUri();
    }

    public URI criarUri(String path,String recurso){
        return UriComponentsBuilder.newInstance()
                .scheme("http").host(host).port(porta)
                .path(path+"/{recurso}").buildAndExpand(recurso).toUri();
    }

    public URI criarUri(String path, int recurso){
        return UriComponentsBuilder.newInstance()
                .scheme("http").host(host).port(porta)
                .path(path+"/{recurso}").buildAndExpand(recurso).toUri();
    }

}
