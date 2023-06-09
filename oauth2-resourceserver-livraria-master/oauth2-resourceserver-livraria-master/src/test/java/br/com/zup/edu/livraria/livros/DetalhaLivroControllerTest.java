package br.com.zup.edu.livraria.livros;

import base.SpringBootIntegrationTest;
import br.com.zup.edu.livraria.autores.Autor;
import br.com.zup.edu.livraria.autores.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DetalhaLivroControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private LivroRepository repository;
    @Autowired
    private AutorRepository autorRepository;

    private Autor AUTOR;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        autorRepository.deleteAll();
        this.AUTOR = autorRepository.save(new Autor("Rafael",
                "rafael.ponte@zup.com.br", "dev cansado"));
    }

    @Test
    public void deveDetalharLivroExistente() throws Exception {

        // cenário
        Livro existente = new Livro("Spring Security",
                "Sobre Spring Security", "978-0-4703-2225-3", AUTOR, LocalDate.now());
        repository.save(existente);

        // ação e validação
        mockMvc.perform(GET("/api/livros/{id}", existente.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("SCOPE_livraria:read"))
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existente.getId()))
                .andExpect(jsonPath("$.nome").value(existente.getNome()))
                .andExpect(jsonPath("$.isbn").value(existente.getIsbn()))
                .andExpect(jsonPath("$.descricao").value(existente.getDescricao()))
                .andExpect(jsonPath("$.autorId").value(AUTOR.getId()))
                .andExpect(jsonPath("$.criadoEm").exists())
                .andExpect(jsonPath("$.publicadoEm").exists())
        ;
    }

    @Test
    public void naoDeveDetalharLivro_quandoNaoEncontrado() throws Exception {
        // cenário
        Livro existente = new Livro("Spring Security",
                "Sobre Spring Security", "978-0-4703-2225-3", AUTOR, LocalDate.now());
        repository.save(existente);

        // ação e validação
        mockMvc.perform(GET("/api/livros/{id}", -99999)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(new SimpleGrantedAuthority("SCOPE_livraria:read"))
                        )
                )
                .andExpect(status().isNotFound())
                .andExpect(status().reason("livro não encontrado"))
        ;
    }

    @Test
    public void naoDeveDetalharLivroExistenteSemToken() throws Exception {

        // cenário
        Livro existente = new Livro("Spring Security",
                "Sobre Spring Security", "978-0-4703-2225-3", AUTOR, LocalDate.now());
        repository.save(existente);

        // ação e validação
        mockMvc.perform(GET("/api/livros/{id}", existente.getId())                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existente.getId()))
                .andExpect(jsonPath("$.nome").value(existente.getNome()))
                .andExpect(jsonPath("$.isbn").value(existente.getIsbn()))
                .andExpect(jsonPath("$.descricao").value(existente.getDescricao()))
                .andExpect(jsonPath("$.autorId").value(AUTOR.getId()))
                .andExpect(jsonPath("$.criadoEm").exists())
                .andExpect(jsonPath("$.publicadoEm").exists())
        ;
    }

    @Test
    public void naoDeveDetalharLivroExistenteComscopoInapropriado() throws Exception {

        // cenário
        Livro existente = new Livro("Spring Security",
                "Sobre Spring Security", "978-0-4703-2225-3", AUTOR, LocalDate.now());
        repository.save(existente);

        // ação e validação
        mockMvc.perform(GET("/api/livros/{id}", existente.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existente.getId()))
                .andExpect(jsonPath("$.nome").value(existente.getNome()))
                .andExpect(jsonPath("$.isbn").value(existente.getIsbn()))
                .andExpect(jsonPath("$.descricao").value(existente.getDescricao()))
                .andExpect(jsonPath("$.autorId").value(AUTOR.getId()))
                .andExpect(jsonPath("$.criadoEm").exists())
                .andExpect(jsonPath("$.publicadoEm").exists())
        ;
    }

}