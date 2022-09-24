package dev.paulovieira.springuninter.services.impl;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.models.enums.Sexo;
import dev.paulovieira.springuninter.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

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

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startClient();
    }

    @Test
    void deveRetornarUmClienteQuandoBuscarPorId() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));

        Cliente clienteEncontrado = service.encontrarPorId(1L);

        assertNotNull(clienteEncontrado);
        assertEquals(NOME, clienteEncontrado.getNome());
        assertEquals(CPF, clienteEncontrado.getCpf());
        assertEquals(NASCIMENTO, clienteEncontrado.getDataNascimento());
        assertEquals(EMAIL, clienteEncontrado.getEmail());
        assertEquals(TELEFONE, clienteEncontrado.getTelefone());
        assertEquals(CELULAR, clienteEncontrado.getCelular());
        assertEquals(SEXO, clienteEncontrado.getSexo());
    }

    @Test
    void deveRetornarUmaListaDeClientes() {
        when(repository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = service.encontrarTodos();

        assertEquals(1, clientes.size());
        assertEquals(NOME, clientes.get(0).getNome());
        assertEquals(CPF, clientes.get(0).getCpf());
        assertEquals(NASCIMENTO, clientes.get(0).getDataNascimento());
        assertEquals(EMAIL, clientes.get(0).getEmail());
        assertEquals(TELEFONE, clientes.get(0).getTelefone());
        assertEquals(CELULAR, clientes.get(0).getCelular());
        assertEquals(SEXO, clientes.get(0).getSexo());
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
        when(repository.findById(any())).thenReturn(Optional.of(cliente));
        doNothing().when(repository).deleteById(any());

        service.excluir(1L);

        verify(repository, times(1)).deleteById(any());
    }

    @Test
    void atualizar() {
        when(repository.findById(any())).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

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
        when(repository.findById(any())).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        service.inativar(1L);

        verify(repository, times(1)).save(any());
    }

    @Test
    void ativar() {
        when(repository.findById(any())).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        service.ativar(1L);

        verify(repository, times(1)).save(any());
    }

    private void startClient() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome(NOME);
        cliente.setCpf(CPF);
        cliente.setDataNascimento(NASCIMENTO);
        cliente.setEmail(EMAIL);
        cliente.setTelefone(TELEFONE);
        cliente.setCelular(CELULAR);
        cliente.setSexo(SEXO);
    }
}