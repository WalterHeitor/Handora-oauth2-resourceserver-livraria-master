package br.com.zup.edu.livrariaclient.livro;

import br.com.zup.edu.livrariaclient.client.DetalhaAutorResponse;
import br.com.zup.edu.livrariaclient.client.LivroResponse;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {
    public DetalheLivroResponse toDetalheLivroResponse(LivroResponse livroResponse, DetalhaAutorResponse autorResponse) {
        return new DetalheLivroResponse(
                livroResponse.getId(),
                livroResponse.getNome(),
                livroResponse.getDescricao(),
                livroResponse.getIsbn(),
                livroResponse.getAutorId(),
                livroResponse.getPublicadoEm(),
                livroResponse.getCriadoEm(),
                new AutorResponse(
                        autorResponse.getNome(),
                        autorResponse.getEmail()
                )
        );
    }
}
