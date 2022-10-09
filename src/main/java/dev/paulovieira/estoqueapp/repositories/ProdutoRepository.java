package dev.paulovieira.estoqueapp.repositories;

import dev.paulovieira.estoqueapp.models.Cliente;
import dev.paulovieira.estoqueapp.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

