package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.repositories.FornecedorRepository;
import dev.paulovieira.estoqueapp.services.FornecedorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe de serviço para fornecedors.
 * @author Paulo Vieira
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional
public class FornecedorServiceImpl implements FornecedorService {

    private static final Log LOG = LogFactory.getLog(FornecedorServiceImpl.class);
    private final FornecedorRepository repository;

    public FornecedorServiceImpl(FornecedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Fornecedor encontrarPorId(Long id) {
        LOG.info("Buscando fornecedor pelo id: " + id);

        return repository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Fornecedor não encontrado")
                );
    }

    @Override
    public List<Fornecedor> encontrarTodos() {
        LOG.info("Buscando todos os fornecedors");
        return repository.findAll();
    }

    @Override
    public Fornecedor salvar(Fornecedor fornecedor) {
        LOG.info("Salvando fornecedor: " + fornecedor.getNomeFantasia());
        return repository.save(fornecedor);
    }

    @Override
    public void excluir(Long id) {
        LOG.info("Excluindo fornecedor com id: " + id);

        // Caso o fornecedor não exista, o método findById irá lançar uma exceção
        Fornecedor fornecedor = encontrarPorId(id);

        repository.deleteById(fornecedor.getId());
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        LOG.info("Atualizando fornecedor com id: " + fornecedor.getId());

        // Caso o fornecedor não exista, o método findById irá lançar uma exceção
        encontrarPorId(fornecedor.getId());

        repository.save(fornecedor);
    }

    @Override
    public void inativar(Long id) {
        LOG.info("Inativando fornecedor com id: " + id);

        // Caso o fornecedor não exista, o método findById irá lançar uma exceção
        Fornecedor fornecedor = encontrarPorId(id);

        fornecedor.setAtivo(false);
        repository.save(fornecedor);
    }

    @Override
    public void ativar(Long id) {
        LOG.info("Ativando fornecedor com id: " + id);

        // Caso o fornecedor não exista, o método findById irá lançar uma exceção
        Fornecedor fornecedor = encontrarPorId(id);

        fornecedor.setAtivo(true);
        repository.save(fornecedor);
    }
}
