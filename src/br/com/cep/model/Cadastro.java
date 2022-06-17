package br.com.cep.model;

public class Cadastro {
    private String nome;
    private int cep;
    private int numero;
    private String complemento;

    public Cadastro(String nome, int cep, int numero, String complemento) {
        this.nome = nome;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return String.valueOf(cep);
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

}
