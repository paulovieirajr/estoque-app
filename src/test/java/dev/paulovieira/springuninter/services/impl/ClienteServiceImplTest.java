package dev.paulovieira.springuninter.services.impl;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.models.enums.Sexo;
import dev.paulovieira.springuninter.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    // Constantes para uso nos testes
    private static final String NOME = "John Doe";
    private static final String CPF = "12345678901";
    private static final LocalDate NASCIMENTO = LocalDate.of(1990, 1, 1);
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String TELEFONE = "1122223333";
    private static final String CELULAR = "11987654321";
    private static final Sexo SEXO = Sexo.MASCULINO;

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        // Inicializa o mockito
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void encontrarPorId() {
        when(repository.findById(any())).thenReturn(Optional.of(new Cliente()));

        Cliente cliente = service.encontrarPorId(1L);

        assertNotNull(cliente);
    }

    @Test
    void encontrarTodos() {
        when(repository.findAll()).thenReturn(List.of(new Cliente()));

        List<Cliente> clientes = service.encontrarTodos();

        assertEquals(1, clientes.size());
    }

    @Test
    void salvar() {
        when(repository.save(any(Cliente.class))).thenReturn(new Cliente());

        Cliente cliente = new Cliente();
        cliente.setNome(NOME);
        cliente.setCpf(CPF);
        cliente.setDataNascimento(NASCIMENTO);
        cliente.setEmail(EMAIL);
        cliente.setTelefone(TELEFONE);
        cliente.setCelular(CELULAR);
        cliente.setSexo(SEXO);

        Cliente clienteSalvo = service.salvar(cliente);

        assertNotNull(clienteSalvo);
    }

    @Test
    void excluir() {
        when(repository.findById(any())).thenReturn(Optional.of(new Cliente()));
        doNothing().when(repository).deleteById(any());

        service.excluir(1L);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void atualizar() {
        when(repository.findById(any())).thenReturn(Optional.of(new Cliente()));
        when(repository.save(any(Cliente.class))).thenReturn(new Cliente());

        Cliente cliente = new Cliente();
        cliente.setNome(NOME);
        cliente.setCpf(CPF);
        cliente.setDataNascimento(NASCIMENTO);
        cliente.setEmail(EMAIL);
        cliente.setTelefone(TELEFONE);
        cliente.setCelular(CELULAR);
        cliente.setSexo(SEXO);

        service.atualizar(cliente);

        verify(repository, times(1)).save(any());
    }

    @Test
    void inativar() {
        when(repository.findById(any())).thenReturn(Optional.of(new Cliente()));
        when(repository.save(any(Cliente.class))).thenReturn(new Cliente());

        service.inativar(1L);

        verify(repository, times(1)).save(any());
    }

    @Test
    void ativar() {
        when(repository.findById(any())).thenReturn(Optional.of(new Cliente()));
        when(repository.save(any(Cliente.class))).thenReturn(new Cliente());

        service.ativar(1L);

        verify(repository, times(1)).save(any());
    }
}