package dev.paulovieira.estoqueapp.repositories;

import dev.paulovieira.estoqueapp.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

