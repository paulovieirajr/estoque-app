package dev.paulovieira.estoqueapp.repositories;

import dev.paulovieira.estoqueapp.models.NotaEntradaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotaEntradaItemRepository extends JpaRepository<NotaEntradaItem, Long> {

    @Query("SELECT n FROM NotaEntradaItem n WHERE n.notaEntrada.id = ?1")
    List<NotaEntradaItem> listaItensPorNota(Long notaEntradaId);
}

