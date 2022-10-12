package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.NotaEntrada;
import dev.paulovieira.estoqueapp.repositories.NotaEntradaRepository;
import dev.paulovieira.estoqueapp.services.NotaEntradaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotaEntradaServiceImpl implements NotaEntradaService {

    NotaEntradaRepository repository;

    public NotaEntradaServiceImpl(NotaEntradaRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotaEntrada encontrarPorId(Long id) {
        Optional<NotaEntrada> notaEntrada = repository.findById(id);

        return notaEntrada.orElseThrow(
                () -> new IllegalArgumentException("Nota de entrada não encontrada")
        );

    }

    @Override
    public List<NotaEntrada> encontrarTodas() {
        return repository.findAll();
    }

    @Override
    public NotaEntrada salvar(NotaEntrada notaEntrada) {
        return repository.save(notaEntrada);
    }

    @Override
    public void excluir(Long id) {
        encontrarPorId(id);
        repository.deleteById(id);
    }

    @Override
    public void atualizar(NotaEntrada notaEntrada) {
        encontrarPorId(notaEntrada.getId());
        repository.save(notaEntrada);
    }
}
