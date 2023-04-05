package br.com.zup.edu.livrariaclient.livro;

import br.com.zup.edu.livrariaclient.client.DetalhaAutorClient;
import br.com.zup.edu.livrariaclient.client.DetalhaAutorResponse;
import br.com.zup.edu.livrariaclient.client.DetalhaLivroClient;
import br.com.zup.edu.livrariaclient.client.LivroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalhaLivroController {

    @Autowired
    DetalhaLivroClient livroClient;

    @Autowired
    DetalhaAutorClient autorClient;

    @Autowired
    LivroMapper mapper;

    @GetMapping("/api/detalha-livros/{id}")
    public ResponseEntity<DetalheLivroResponse> detalha(@PathVariable Long id) {

        LivroResponse livroResponse = livroClient.detalha(id);
        DetalhaAutorResponse autorResponse = autorClient.detalha(livroResponse.getAutorId());

        DetalheLivroResponse detalheLivroResponse =
                mapper.toDetalheLivroResponse(livroResponse,autorResponse);

        return  ResponseEntity.ok(detalheLivroResponse);
    }
}
