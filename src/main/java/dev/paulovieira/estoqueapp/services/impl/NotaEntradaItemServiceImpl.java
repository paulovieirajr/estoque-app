package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.NotaEntradaItem;
import dev.paulovieira.estoqueapp.repositories.NotaEntradaItemRepository;
import dev.paulovieira.estoqueapp.services.NotaEntradaItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotaEntradaItemServiceImpl implements NotaEntradaItemService {

    NotaEntradaItemRepository repository;

    public NotaEntradaItemServiceImpl(NotaEntradaItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotaEntradaItem encontrarPorId(Long id) {
        Optional<NotaEntradaItem> notaEntradaItem = repository.findById(id);

        return notaEntradaItem.orElseThrow(
                () -> new IllegalArgumentException("Nota item de entrada não encontrada")
        );

    }

    @Override
    public List<NotaEntradaItem> encontrarTodas() {
        return repository.findAll();
    }

    @Override
    public NotaEntradaItem salvar(NotaEntradaItem notaEntradaItem) {
        return repository.save(notaEntradaItem);
    }

    @Override
    public void excluir(Long id) {
        encontrarPorId(id);
        repository.deleteById(id);
    }

    @Override
    public void atualizar(NotaEntradaItem notaEntradaItem) {
        encontrarPorId(notaEntradaItem.getId());
        repository.save(notaEntradaItem);
    }

    @Override
    public List<NotaEntradaItem> listaItensPorNota(Long notaEntradaId) {
        return repository.listaItensPorNota(notaEntradaId);
    }
}
