package com.EmprestimoMinsaitLarissa.Emprestimo.controller;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteJaCadastradoException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ClienteController {
   private ClienteService clienteService;


   @Autowired
   public ClienteController(ClienteService clienteService){
       this.clienteService = clienteService;
   }

    @GetMapping("/clientes")
    public List<Cliente> clientesCadastrados(){
        return this.clienteService.clientesCadastrados();
    }

    @GetMapping("/clientes/{cpf}")
    public Cliente obterDadosCliente(@PathVariable String cpf) throws ClienteNotFoundException {
        return this.clienteService.obterDadosCliente(cpf);
    }

   @PostMapping("/clientes")
   @ResponseStatus(HttpStatus.CREATED)
   public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) throws ClienteJaCadastradoException {
       return this.clienteService.cadastrarCliente(cliente);
   }

   @DeleteMapping("/clientes/{cpf}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletaCliente(@PathVariable String cpf) throws ClienteNotFoundException{
       this.clienteService.deletaCliente(cpf);
   }

   @PutMapping("/clientes/{cpf}")
   public Cliente atualizaCliente(@Valid @RequestBody Cliente cliente, @PathVariable String cpf) throws ClienteNotFoundException{
       return this.clienteService.atualizaCliente(cliente, cpf);
   }


}
