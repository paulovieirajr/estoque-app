package dev.paulovieira.springuninter.config;

import dev.paulovieira.springuninter.models.Cliente;
import dev.paulovieira.springuninter.models.enums.Sexo;
import dev.paulovieira.springuninter.repositories.ClienteRepository;
import dev.paulovieira.springuninter.services.impl.ClienteServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabaseConfig implements CommandLineRunner {

    final ClienteRepository repository;
    final ClienteServiceImpl service;

    public LoadDatabaseConfig(ClienteRepository repository, ClienteServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        cliente.setCpf("12345678901");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setEmail("johndoe@gmail.com");
        cliente.setTelefone("1122223333");
        cliente.setCelular("11987654321");
        cliente.setSexo(Sexo.MASCULINO);

        service.salvar(cliente);
    }
}
