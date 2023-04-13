package com.EmprestimoMinsaitLarissa.Emprestimo.service;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteJaCadastradoException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> clientesCadastrados() {
        return this.clienteRepository.findAll();
    }

    public Cliente obterDadosCliente(String cpf) throws ClienteNotFoundException {
        Cliente cliente = this.clienteRepository.findByCpf(cpf).orElseThrow(()->new ClienteNotFoundException(cpf));
        return cliente;
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException {
        if (clienteRepository.existsByCpf(cliente.getCpf())){
            throw new ClienteJaCadastradoException(cliente.getCpf());
        }
        return this.clienteRepository.save(cliente);
    }

    @Transactional
    public void deletaCliente(String cpf) throws ClienteNotFoundException {
        if (!this.clienteRepository.existsByCpf(cpf)){
            throw new ClienteNotFoundException(cpf);
        }
        this.clienteRepository.deleteByCpf(cpf);
    }

    @Transactional
    public Cliente atualizaCliente(Cliente cliente, String cpf) throws ClienteNotFoundException {
        if (!this.clienteRepository.existsByCpf(cpf)){
            throw new ClienteNotFoundException(cpf);
        }
        Cliente clienteAtualizar = this.clienteRepository.findByCpf(cpf).get();
        if (null!=cliente.getCpf())
            clienteAtualizar.setCpf(cliente.getCpf());
        if (null!=cliente.getEmprestimo())
            clienteAtualizar.setEmprestimo(cliente.getEmprestimo());
        if (null!=cliente.getEndereco())
            clienteAtualizar.setEndereco(cliente.getEndereco());
        if (null!=cliente.getNomeCompleto())
            clienteAtualizar.setNomeCompleto(cliente.getNomeCompleto());
        if (null!=cliente.getRendimentoMensal())
            clienteAtualizar.setRendimentoMensal();
        if (null!=cliente.getTelefone())
            clienteAtualizar.setTelefone(cliente.getTelefone());
        return this.clienteRepository.save(clienteAtualizar);
    }
}
