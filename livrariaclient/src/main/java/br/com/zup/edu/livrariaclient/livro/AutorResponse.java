package br.com.zup.edu.livrariaclient.livro;

public class AutorResponse {
    private final String nome;
    private final String email;

    public AutorResponse(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
