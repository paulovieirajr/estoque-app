package dev.paulovieira.estoqueapp.services;

import dev.paulovieira.estoqueapp.models.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente encontrarPorId(Long id);
    List<Cliente> encontrarTodos();
    Cliente salvar(Cliente cliente);
    void excluir(Long id);
    void atualizar(Cliente cliente);
    void inativar(Long id);
    void ativar(Long id);
}
