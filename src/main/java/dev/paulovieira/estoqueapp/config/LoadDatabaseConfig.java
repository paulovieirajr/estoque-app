package dev.paulovieira.estoqueapp.config;

import dev.paulovieira.estoqueapp.models.Cliente;
import dev.paulovieira.estoqueapp.models.Fornecedor;
import dev.paulovieira.estoqueapp.models.Produto;
import dev.paulovieira.estoqueapp.models.enums.Categoria;
import dev.paulovieira.estoqueapp.models.enums.Sexo;
import dev.paulovieira.estoqueapp.repositories.ClienteRepository;
import dev.paulovieira.estoqueapp.repositories.FornecedorRepository;
import dev.paulovieira.estoqueapp.repositories.ProdutoRepository;
import dev.paulovieira.estoqueapp.services.impl.ClienteServiceImpl;
import dev.paulovieira.estoqueapp.services.impl.FornecedorServiceImpl;
import dev.paulovieira.estoqueapp.services.impl.ProdutoServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class LoadDatabaseConfig implements CommandLineRunner {

    final ClienteRepository clienteRepository;
    final ClienteServiceImpl clienteService;

    final FornecedorRepository fornecedorRepository;
    final FornecedorServiceImpl fornecedorService;

    final ProdutoRepository produtoRepository;
    final ProdutoServiceImpl produtoService;

    public LoadDatabaseConfig(ClienteRepository clienteRepository, ClienteServiceImpl clienteService,
                              FornecedorRepository fornecedorRepository, FornecedorServiceImpl fornecedorService,
                              ProdutoRepository produtoRepository, ProdutoServiceImpl produtoService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorService = fornecedorService;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Salva um cliente no banco de dados
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpf("618.857.170-75");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setEmail("johndoe@gmail.com");
        cliente.setTelefone("(11) 2222-3333");
        cliente.setCelular("(11) 98765-4321");
        cliente.setSexo(Sexo.MASCULINO);

        clienteService.salvar(cliente);

        // Salva um fornecedor no banco de dados
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFantasia("Fábrica de Software");
        fornecedor.setRazaoSocial("Fábrica de Software LTDA");
        fornecedor.setCnpj("74.411.334/0001-94");
        fornecedor.setTelefone("(11) 2455-3887");
        fornecedor.setCelular("(11) 97788-7766");
        fornecedor.setEmail("fabricasofware@gmail.com");

        fornecedorService.salvar(fornecedor);

        // Salva alguns produtos no banco de dados

        var produto1 = new Produto();
        produto1.setNome("Computador");
        produto1.setCategoria(Categoria.ELETRONICO);

        var produto2 = new Produto();
        produto2.setNome("Tênis Nike");
        produto2.setCategoria(Categoria.CALCADO);

        var produto3 = new Produto();
        produto3.setNome("Camiseta Polo");
        produto3.setCategoria(Categoria.ROUPA);

        var produto4 = new Produto();
        produto4.setNome("Cadeira Gamer");
        produto4.setCategoria(Categoria.MOVEIS);

        var produto5 = new Produto();
        produto5.setNome("Café Pimpinela");
        produto5.setCategoria(Categoria.ALIMENTICIO);

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
    }
}
