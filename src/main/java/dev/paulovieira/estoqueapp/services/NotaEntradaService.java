package dev.paulovieira.estoqueapp.services;

import dev.paulovieira.estoqueapp.models.NotaEntrada;

import java.util.List;

public interface NotaEntradaService {

    NotaEntrada encontrarPorId(Long id);
    List<NotaEntrada> encontrarTodas();
    NotaEntrada salvar(NotaEntrada notaEntrada);
    void excluir(Long id);
    void atualizar(NotaEntrada notaEntrada);
}
