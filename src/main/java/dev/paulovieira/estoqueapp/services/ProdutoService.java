package dev.paulovieira.estoqueapp.services;

import dev.paulovieira.estoqueapp.models.Produto;

import java.util.List;

public interface ProdutoService {

    Produto encontrarPorId(Long id);
    List<Produto> encontrarTodos();
    Produto salvar(Produto cliente);
    void excluir(Long id);
    void atualizar(Produto cliente);
    void inativar(Long id);
    void ativar(Long id);
}
