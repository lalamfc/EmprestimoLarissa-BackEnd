package com.EmprestimoMinsaitLarissa.Emprestimo.enums;

public enum Relacionamento {
    BRONZE("Cliente Bronze"),
    PRATA("Cliente Prata"),
    OURO("Cliente Ouro");

    private String relacionamento;

    private Relacionamento (String relacionamentoCliente){

        this.relacionamento = relacionamento;
    }

    private String getRelacionamentoCliente(){

        return this.relacionamento;
    }

}



