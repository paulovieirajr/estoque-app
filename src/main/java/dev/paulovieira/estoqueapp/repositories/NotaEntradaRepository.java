package dev.paulovieira.estoqueapp.repositories;

import dev.paulovieira.estoqueapp.models.Cliente;
import dev.paulovieira.estoqueapp.models.NotaEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Long> {
}

