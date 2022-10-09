package dev.paulovieira.estoqueapp.repositories;

import dev.paulovieira.estoqueapp.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
