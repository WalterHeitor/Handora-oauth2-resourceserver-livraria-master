package br.com.zup.edu.livrariaclient.livro;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DetalheLivroResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String isbn;
    private Long autorId;
    private LocalDate publicadoEm;
    private LocalDateTime criadoEm;
    private AutorResponse autor;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getAutorId() {
        return autorId;
    }

    public LocalDate getPublicadoEm() {
        return publicadoEm;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public DetalheLivroResponse(Long id, String nome, String descricao, String isbn, Long autorId,
                                LocalDate publicadoEm, LocalDateTime criadoEm, AutorResponse autor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.isbn = isbn;
        this.autorId = autorId;
        this.publicadoEm = publicadoEm;
        this.criadoEm = criadoEm;
        this.autor = autor;
    }
}
