package br.com.matheusletra.cadastroclientes;

public class Cliente {
    private int codigo;
    private String nome;
    private String endereco;
    private String email;

    // Construtor vazio
    public Cliente() {
    }

    // Construtor com todos os atributos
    public Cliente(int codigo, String nome, String endereco, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    // Getters e Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

