package br.com.zup.edu.livrariaclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "detalhaAutorClient",
        url = "${integrations.resource-server.url}")
public interface DetalhaAutorClient {

    @GetMapping("/api/autores/{id}")
    public DetalhaAutorResponse detalha(@PathVariable Long id);
}
