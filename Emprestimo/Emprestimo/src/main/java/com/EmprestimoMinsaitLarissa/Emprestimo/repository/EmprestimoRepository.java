package com.EmprestimoMinsaitLarissa.Emprestimo.repository;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    //para chamar o emprestimo pelo cliente

    boolean existsById(Long id);
    List<Emprestimo> findByCliente(Cliente cliente);
}
