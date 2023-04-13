package com.EmprestimoMinsaitLarissa.Emprestimo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cliente_id")
    private Long id;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "nomeCompleto", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "cpf", length = 15, nullable = false)
    //@CPF(message = "O cpf deve ser válido!")
    @Length(max = 15, message = "O cpf não pode ter mais que 11 digitos!")
    private String cpf;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "telefone", length = 15, nullable = false)
    @Length(max = 15, message = "O número do telefone não pode ter mais que 11 digitos!")
    private String telefone;


    @Embedded
    private Endereco endereco;

    //@NotBlank(message = "Campo obrigatório!")
    @Column(name = "rendimento", precision = 20, scale = 2, nullable = false)
    private BigDecimal rendimentoMensal;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos;

    public Cliente() {

        super();
    }

    public Cliente(Long id, String nomeCompleto, String cpf, String telefone, Endereco endereco, BigDecimal rendimentoMensal,
                   List<Emprestimo> emprestimos, BigDecimal valorInicial) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.rendimentoMensal = rendimentoMensal;
        this.emprestimos = emprestimos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal() {
        this.rendimentoMensal = rendimentoMensal;
    }

    public List<Emprestimo> getEmprestimo() {
        if (emprestimos == null)
            emprestimos = new ArrayList<Emprestimo>();
        return emprestimos;
    }

    public void setEmprestimo(List<Emprestimo> emprestimo) {
        this.emprestimos = emprestimos;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

}
