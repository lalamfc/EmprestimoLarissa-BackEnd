package com.EmprestimoMinsaitLarissa.Emprestimo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Emprestimo;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ClienteNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.EmprestimoNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.excecoes.ValorInicialNotFoundException;
import com.EmprestimoMinsaitLarissa.Emprestimo.repository.ClienteRepository;
import com.EmprestimoMinsaitLarissa.Emprestimo.repository.EmprestimoRepository;
import com.EmprestimoMinsaitLarissa.Emprestimo.util.VerificaValorFinalUtil;

import jakarta.transaction.Transactional;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;

    private ClienteRepository clienteRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository){
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Emprestimo> listarEmprestimosPorCpf(String cpf) {
       List<Emprestimo> emprestimos = this.emprestimoRepository.findAll();
       emprestimos.stream().filter(emprestimo -> emprestimo.getCliente().getCpf() == cpf).collect(Collectors.toList());
       return emprestimos;
    }

    public boolean valorInicialEValido(BigDecimal rendimentoMensal,  BigDecimal valorInicial) throws ValorInicialNotFoundException{
        BigDecimal limiteMaxRendimento = ( rendimentoMensal.multiply(BigDecimal.valueOf(10)));
        if (valorInicial.compareTo(limiteMaxRendimento) == 1){
            throw new ValorInicialNotFoundException(valorInicial);
        }
        return false;
    }

    @Transactional
    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo, String cpf) throws ClienteNotFoundException, ValorInicialNotFoundException {
        Cliente cliente = this.clienteRepository.findByCpf(cpf).orElseThrow(()-> new ClienteNotFoundException(cpf));
        if (this.valorInicialEValido(cliente.getRendimentoMensal(), emprestimo.getValorInicial())){
            throw new ValorInicialNotFoundException(emprestimo.getValorInicial());
        }
        emprestimo.setValorFinal(VerificaValorFinalUtil.obterValorFinalCliente(emprestimo.getRelacionamento(),emprestimo.getValorInicial(),cliente.getEmprestimo().size()));
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFinal = dataAtual.plusDays(30);
        emprestimo.setDataInicial(dataAtual);
        emprestimo.setDataFinal(dataFinal);
        emprestimo.setCliente(cliente);
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo obterDadosEmprest(Cliente cliente) throws EmprestimoNotFoundException, ClienteNotFoundException {
        Optional<Cliente> clienteCadastrado = this.clienteRepository.findByCpf(cliente.getCpf());
        if (clienteCadastrado.isEmpty()){
            throw new ClienteNotFoundException(cliente.getCpf());
        }
        //List<Emprestimo> emprestimos = new ArrayList<>(this.emprestimoRepository.findByCliente(clienteCadastrado.get())).stream().filter(emprestimo -> emprestimo.getId()==cliente.getEmprestimo().get(0).getId()).collect(Collectors.toList());

        List<Emprestimo> emprestimos = this.emprestimoRepository.findByCliente(clienteCadastrado.get());
        for (Emprestimo emprestimo: emprestimos){
            if (emprestimo.getId() == cliente.getEmprestimo().get(0).getId()){
                return emprestimo;
            }
        }
        return null;
    }

    @Transactional
    public void deletaEmprestimo(Long id) throws EmprestimoNotFoundException {
        if (!this.emprestimoRepository.existsById(id)){
            throw new EmprestimoNotFoundException(id);
        }
        this.emprestimoRepository.deleteById(id);
    }


}
