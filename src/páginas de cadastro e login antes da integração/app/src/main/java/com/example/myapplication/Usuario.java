package com.example.myapplication;

public class Usuario {
    private String nome;
    private String curso;
    private int semestre;
    private String cpf;
    private String ra;
    private String email;
    private String senha;
    private String dataNascimento;

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Getter
    public String getNome() {
        return nome;
    }

    // (Opcional) Outros getters caso precise depois
    public String getCurso() {
        return curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRa() {
        return ra;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}

