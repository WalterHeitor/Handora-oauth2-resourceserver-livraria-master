package br.com.zup.edu.livraria.autores;

import base.SpringBootIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RemoveAutorControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private AutorRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveRemoverAutorExistente() throws Exception {
        // cenário
        Autor autor = new Autor("Rafael","rafael.ponte@zup.com.br", "dev cansado");
        repository.save(autor);

        // ação
        mockMvc.perform(DELETE("/api/autores/{id}", autor.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("SCOPE_livraria:write"))
                        )
                )
                .andExpect(status().isNoContent())
        ;

        // validação
        assertEquals(0, repository.count(), "total de albuns");
    }

    @Test
    public void deveRemoverAutor_quandoNaoEncontrado() throws Exception {
        // cenário
        Autor autor = new Autor("Rafael","rafael.ponte@zup.com.br", "dev cansado");
        repository.save(autor);

        // ação
        mockMvc.perform(DELETE("/api/autores/{id}", -99999)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("SCOPE_livraria:write"))
                        )
                )
                .andExpect(status().isNotFound())
                .andExpect(status().reason("autor não encontrado"))
        ;

        // validação
        assertEquals(1, repository.count(), "total de albuns");
    }

    @Test
    public void naoDeveRemoverAutorExistenteSemToken() throws Exception {
        // cenário
        Autor autor = new Autor("Rafael","rafael.ponte@zup.com.br", "dev cansado");
        repository.save(autor);

        // ação
        mockMvc.perform(DELETE("/api/autores/{id}", autor.getId()))
                .andExpect(status().isUnauthorized())
        ;

        // validação
        assertEquals(1, repository.count(), "total de albuns");
    }

    @Test
    public void naoDeveRemoverAutorExistenteScopeInapropriado() throws Exception {
        // cenário
        Autor autor = new Autor("Rafael","rafael.ponte@zup.com.br", "dev cansado");
        repository.save(autor);

        // ação
        mockMvc.perform(DELETE("/api/autores/{id}", autor.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isForbidden())
        ;

        // validação
        assertEquals(1, repository.count(), "total de albuns");
    }

}