package dev.paulovieira.estoqueapp.services.impl;

import dev.paulovieira.estoqueapp.models.Produto;
import dev.paulovieira.estoqueapp.models.enums.Categoria;
import dev.paulovieira.estoqueapp.repositories.ProdutoRepository;
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

class ProdutoServiceImplTest {


    public static final String NOME = "Computador";
    public static final Categoria ELETRONICO = Categoria.ELETRONICO;

    public static final String NOME_ALTERNATIVO = "Cadeira";
    public static final Categoria MOVEL = Categoria.MOVEIS;
    @InjectMocks
    private ProdutoServiceImpl service;

    @Mock
    private ProdutoRepository repository;

    private Produto produto;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startProduto();
    }

    @Test
    void deveRetornarUmProdutoQuandoBuscarPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(produto));

        var produto = service.encontrarPorId(1L);

        assertNotNull(produto);
        assertEquals(NOME, produto.getNome());
        assertEquals(ELETRONICO, produto.getCategoria());
    }

    @Test
    void deveRetornarUmaListaDeProdutos() {
        when(repository.findAll()).thenReturn(List.of(produto));

        List<Produto> produtoes = service.encontrarTodos();

        assertEquals(1, produtoes.size());
        assertEquals(NOME, produtoes.get(0).getNome());
        assertEquals(ELETRONICO, produtoes.get(0).getCategoria());
    }

    @Test
    void salvar() {
        when(repository.save(any(Produto.class))).thenReturn(new Produto());

        Produto produto = new Produto();
        produto.setNome(NOME);

        Produto produtoSalvo = service.salvar(produto);

        assertNotNull(produtoSalvo);
    }

    @Test
    void excluir() {
        when(repository.findById(any())).thenReturn(Optional.of(produto));
        doNothing().when(repository).deleteById(any());

        service.excluir(1L);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void atualizar() {
        when(repository.findById(any())).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenReturn(produto);

        Produto produto = new Produto();
        produto.setNome(NOME_ALTERNATIVO);
        produto.setCategoria(MOVEL);

        service.atualizar(produto);

        verify(repository, times(1)).save(any());
    }

    @Test
    void inativar() {
        when(repository.findById(any())).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenReturn(produto);

        service.inativar(1L);

        verify(repository, times(1)).save(any());
    }

    @Test
    void ativar() {
        when(repository.findById(any())).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenReturn(produto);

        service.ativar(1L);

        verify(repository, times(1)).save(any());
    }

    private void startProduto() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome(NOME);
        produto.setCategoria(ELETRONICO);
    }
}