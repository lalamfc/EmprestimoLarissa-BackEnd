package com.EmprestimoMinsaitLarissa.Emprestimo.entity;

import jakarta.persistence.*;


@Embeddable
public class Endereco{

    private String cep;
    private String rua;
    private Integer numero;

    public Endereco(String cep, String rua, Integer numero) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
    }

    public Endereco() {

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}

