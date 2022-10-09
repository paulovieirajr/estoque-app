package dev.paulovieira.estoqueapp.config;

import dev.paulovieira.estoqueapp.models.Cliente;
import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.models.enums.Sexo;
import dev.paulovieira.estoqueapp.repositories.ClienteRepository;
import dev.paulovieira.estoqueapp.repositories.FornecedorRepository;
import dev.paulovieira.estoqueapp.services.impl.ClienteServiceImpl;
import dev.paulovieira.estoqueapp.services.impl.FornecedorServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabaseConfig implements CommandLineRunner {

    final ClienteRepository clienteRepository;
    final ClienteServiceImpl clienteService;

    final FornecedorRepository fornecedorRepository;
    final FornecedorServiceImpl fornecedorService;

    public LoadDatabaseConfig(ClienteRepository clienteRepository, ClienteServiceImpl clienteService, FornecedorRepository fornecedorRepository, FornecedorServiceImpl fornecedorService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorService = fornecedorService;
    }

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpf("618.857.170-75");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setEmail("johndoe@gmail.com");
        cliente.setTelefone("(11) 2222-3333");
        cliente.setCelular("(11) 98765-4321");
        cliente.setSexo(Sexo.MASCULINO);

        clienteService.salvar(cliente);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFantasia("Fábrica de Software");
        fornecedor.setRazaoSocial("Fábrica de Software LTDA");
        fornecedor.setCnpj("74.411.334/0001-94");
        fornecedor.setTelefone("(11) 2455-3887");
        fornecedor.setCelular("(11) 97788-7766");
        fornecedor.setEmail("fabricasofware@gmail.com");

        fornecedorService.salvar(fornecedor);
    }
}
