package br.com.zup.edu.livrariaclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "livroClient",
        url = "${integrations.resource-server.url}")
public interface DetalhaLivroClient {  ///api/autores/1

    @GetMapping("/api/livros/{id}")
    public LivroResponse detalha(@PathVariable Long id);
}
