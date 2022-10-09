package dev.paulovieira.estoqueapp.services;

import dev.paulovieira.estoqueapp.models.Fornecedor;

import java.util.List;

public interface FornecedorService {

    Fornecedor encontrarPorId(Long id);
    List<Fornecedor> encontrarTodos();
    Fornecedor salvar(Fornecedor fornecedor);
    void excluir(Long id);
    void atualizar(Fornecedor fornecedor);
    void inativar(Long id);
    void ativar(Long id);
}
