package br.com.zup.edu.livrariaclient.client;

public class DetalhaAutorResponse {
    private final String nome;
    private final String email;

    public DetalhaAutorResponse(String nome, String email) {
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
