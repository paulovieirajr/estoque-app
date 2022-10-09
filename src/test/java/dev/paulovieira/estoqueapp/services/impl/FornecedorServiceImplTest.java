package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.repositories.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class FornecedorServiceImplTest {

    // Constantes para uso nos testes
    private static final String NOME_FANTASIA = "Fábrica de Software";
    private static final String CNPJ = "74.411.334/0001-94";
    private static final String RAZAO_SOCIAL = "Fábrica de Software LTDA";
    private static final String EMAIL = "fabricasoftware@gmail.com";
    private static final String TELEFONE = "(11) 2455-3887";
    private static final String CELULAR = "(11) 97788-7766";

    @InjectMocks
    private FornecedorServiceImpl service;

    @Mock
    private FornecedorRepository repository;

    private Fornecedor fornecedor;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startFornecedor();
    }

    @Test
    void deveRetornarUmFornecedorQuandoBuscarPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(fornecedor));

        var fornecedor = service.encontrarPorId(1L);

        assertNotNull(fornecedor);
        assertEquals(NOME_FANTASIA, fornecedor.getNomeFantasia());
        assertEquals(CNPJ, fornecedor.getCnpj());
        assertEquals(RAZAO_SOCIAL, fornecedor.getRazaoSocial());
        assertEquals(EMAIL, fornecedor.getEmail());
        assertEquals(TELEFONE, fornecedor.getTelefone());
        assertEquals(CELULAR, fornecedor.getCelular());
    }

    @Test
    void deveRetornarUmaListaDeFornecedors() {
        when(repository.findAll()).thenReturn(List.of(fornecedor));

        List<Fornecedor> fornecedores = service.encontrarTodos();

        assertEquals(1, fornecedores.size());
        assertEquals(NOME_FANTASIA, fornecedores.get(0).getNomeFantasia());
        assertEquals(CNPJ, fornecedores.get(0).getCnpj());
        assertEquals(RAZAO_SOCIAL, fornecedores.get(0).getRazaoSocial());
        assertEquals(EMAIL, fornecedores.get(0).getEmail());
        assertEquals(TELEFONE, fornecedores.get(0).getTelefone());
        assertEquals(CELULAR, fornecedores.get(0).getCelular());
    }

    @Test
    void salvar() {
        when(repository.save(any(Fornecedor.class))).thenReturn(new Fornecedor());

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFantasia(NOME_FANTASIA);
        fornecedor.setCnpj(CNPJ);
        fornecedor.setRazaoSocial(RAZAO_SOCIAL);
        fornecedor.setEmail(EMAIL);
        fornecedor.setTelefone(TELEFONE);
        fornecedor.setCelular(CELULAR);

        Fornecedor fornecedorSalvo = service.salvar(fornecedor);

        assertNotNull(fornecedorSalvo);
    }

    @Test
    void excluir() {
        when(repository.findById(any())).thenReturn(Optional.of(fornecedor));
        doNothing().when(repository).deleteById(any());

        service.excluir(1L);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void atualizar() {
        when(repository.findById(any())).thenReturn(Optional.of(fornecedor));
        when(repository.save(any(Fornecedor.class))).thenReturn(fornecedor);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFantasia(NOME_FANTASIA);
        fornecedor.setCnpj(CNPJ);
        fornecedor.setRazaoSocial(RAZAO_SOCIAL);
        fornecedor.setEmail(EMAIL);
        fornecedor.setTelefone(TELEFONE);
        fornecedor.setCelular(CELULAR);

        service.atualizar(fornecedor);

        verify(repository, times(1)).save(any());
    }

    @Test
    void inativar() {
        when(repository.findById(any())).thenReturn(Optional.of(fornecedor));
        when(repository.save(any(Fornecedor.class))).thenReturn(fornecedor);

        service.inativar(1L);

        verify(repository, times(1)).save(any());
    }

    @Test
    void ativar() {
        when(repository.findById(any())).thenReturn(Optional.of(fornecedor));
        when(repository.save(any(Fornecedor.class))).thenReturn(fornecedor);

        service.ativar(1L);

        verify(repository, times(1)).save(any());
    }

    private void startFornecedor() {
        fornecedor = new Fornecedor();
        fornecedor.setId(1L);
        fornecedor.setNomeFantasia(NOME_FANTASIA);
        fornecedor.setCnpj(CNPJ);
        fornecedor.setRazaoSocial(RAZAO_SOCIAL);
        fornecedor.setEmail(EMAIL);
        fornecedor.setTelefone(TELEFONE);
        fornecedor.setCelular(CELULAR);
    }
}