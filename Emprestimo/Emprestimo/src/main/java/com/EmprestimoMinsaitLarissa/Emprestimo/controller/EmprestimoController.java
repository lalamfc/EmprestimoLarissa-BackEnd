package com.EmprestimoMinsaitLarissa.Emprestimo.controller;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Emprestimo;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.EmprestimoNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ValorInicialNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService){

        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/clientes/{cpf}/emprestimos")
    public List<Emprestimo> listarEmprestimos(String cpf){
        return emprestimoService.listarEmprestimosPorCpf(cpf);
    }

    @GetMapping("/clientes/{cpf}/emprestimos/{id}")
    public Emprestimo obterDadosEmprest(@PathVariable String cpf, @PathVariable Long id) throws ClienteNotFoundException, EmprestimoNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(id);
        cliente.getEmprestimo().add(emprestimo);
        return this.emprestimoService.obterDadosEmprest(cliente);
    }
    @PostMapping("/clientes/{cpf}/emprestimos")
    @ResponseStatus(HttpStatus.CREATED)
    public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo, @PathVariable String cpf) throws ClienteNotFoundException, ValorInicialNotFoundException {
        return this.emprestimoService.cadastrarEmprestimo(emprestimo,cpf);
    }

    @DeleteMapping("/clientes/{cpf}/emprestimos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaEmprest(@PathVariable Long id) throws EmprestimoNotFoundException {
        this.emprestimoService.deletaEmprestimo(id);
    }
}
