package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.Produto;
import dev.paulovieira.estoqueapp.repositories.ProdutoRepository;
import dev.paulovieira.estoqueapp.services.ProdutoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe de serviço para produtos.
 * @author Paulo Vieira
 * @since 1.0
 * @version 1.0
 */
@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    private static final Log LOG = LogFactory.getLog(ProdutoServiceImpl.class);
    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto encontrarPorId(Long id) {
        LOG.info("Buscando produto pelo id: " + id);

        return repository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Produto não encontrado")
                );
    }

    @Override
    public List<Produto> encontrarTodos() {
        LOG.info("Buscando todos os produtos");
        return repository.findAll();
    }

    @Override
    public Produto salvar(Produto produto) {
        LOG.info("Salvando produto: " + produto.getNome());
        return repository.save(produto);
    }

    @Override
    public void excluir(Long id) {
        LOG.info("Excluindo produto com id: " + id);

        // Caso o produto não exista, o método findById irá lançar uma exceção
        Produto produto = encontrarPorId(id);

        repository.deleteById(produto.getId());
    }

    @Override
    public void atualizar(Produto produto) {
        LOG.info("Atualizando produto com id: " + produto.getId());

        // Caso o produto não exista, o método findById irá lançar uma exceção
        encontrarPorId(produto.getId());

        repository.save(produto);
    }

    @Override
    public void inativar(Long id) {
        LOG.info("Inativando produto com id: " + id);

        // Caso o produto não exista, o método findById irá lançar uma exceção
        Produto produto = encontrarPorId(id);

        produto.setAtivo(false);
        repository.save(produto);
    }

    @Override
    public void ativar(Long id) {
        LOG.info("Ativando produto com id: " + id);

        // Caso o produto não exista, o método findById irá lançar uma exceção
        Produto produto = encontrarPorId(id);

        produto.setAtivo(true);
        repository.save(produto);
    }
}
