package com.EmprestimoMinsaitLarissa.Emprestimo.repository;

import com.EmprestimoMinsaitLarissa.Emprestimo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> deleteByCpf(String cpf);
}
