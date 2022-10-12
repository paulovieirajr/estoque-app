package dev.paulovieira.estoqueapp.services;

import dev.paulovieira.estoqueapp.models.NotaEntradaItem;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface NotaEntradaItemService {

    NotaEntradaItem encontrarPorId(Long id);
    List<NotaEntradaItem> encontrarTodas();
    NotaEntradaItem salvar(NotaEntradaItem notaEntradaItem);
    void excluir(Long id);
    void atualizar(NotaEntradaItem notaEntradaItem);
    List<NotaEntradaItem> listaItensPorNota(Long notaEntradaId);
}
