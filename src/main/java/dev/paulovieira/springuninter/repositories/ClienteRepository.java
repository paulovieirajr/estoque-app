package dev.paulovieira.springuninter.repositories;

import dev.paulovieira.springuninter.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

