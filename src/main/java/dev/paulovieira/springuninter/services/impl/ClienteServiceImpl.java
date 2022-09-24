package dev.paulovieira.springuninter.services.impl;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.repositories.ClienteRepository;
import dev.paulovieira.springuninter.services.ClienteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviço para clientes.
 * @author Paulo Vieira
 * @since 1.0
 * @version 1.0
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Log LOG = LogFactory.getLog(ClienteServiceImpl.class);
    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente encontrarPorId(Long id) {
        LOG.info("Buscando cliente pelo id: " + id);

        return repository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Cliente não encontrado")
                );
    }

    @Override
    public List<Cliente> encontrarTodos() {
        LOG.info("Buscando todos os clientes");
        return repository.findAll();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        LOG.info("Salvando cliente: " + cliente.getNome());
        return repository.save(cliente);
    }

    @Override
    public void excluir(Long id) {
        LOG.info("Excluindo cliente com id: " + id);

        // Caso o cliente não exista, o método findById irá lançar uma exceção
        Cliente cliente = encontrarPorId(id);

        repository.deleteById(cliente.getId());
    }

    @Override
    public void atualizar(Cliente cliente) {
        LOG.info("Atualizando cliente com id: " + cliente.getId());

        // Caso o cliente não exista, o método findById irá lançar uma exceção
        encontrarPorId(cliente.getId());

        repository.save(cliente);
    }

    @Override
    public void inativar(Long id) {
        LOG.info("Inativando cliente com id: " + id);

        // Caso o cliente não exista, o método findById irá lançar uma exceção
        Cliente cliente = encontrarPorId(id);

        cliente.setAtivo(false);
        repository.save(cliente);
    }

    @Override
    public void ativar(Long id) {
        LOG.info("Ativando cliente com id: " + id);

        // Caso o cliente não exista, o método findById irá lançar uma exceção
        Cliente cliente = encontrarPorId(id);

        cliente.setAtivo(true);
        repository.save(cliente);
    }
}
