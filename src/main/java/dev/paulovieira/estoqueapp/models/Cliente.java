package dev.paulovieira.estoqueapp.models;

import dev.paulovieira.estoqueapp.models.enums.Sexo;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * Classe que representa um cliente.
 * @author Paulo Vieira
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nome;

    @CPF(message = "CPF inválido, por favor, verifique")
    @Size(min = 14, max = 14)
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Past(message = "A data de nascimento deve ser no passado")
    @NotNull(message = "Informe a data de nascimento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @NotNull(message = "Informe o sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Size(max = 14)
    @Column(length = 14, unique = true)
    private String telefone;

    @NotBlank(message = "Celular inválido, por favor, verifique")
    @Size(min = 15, max = 15)
    @Column(length = 15, unique = true)
    private String celular;

    @NotBlank
    @Email(message = "E-mail inválido")
    @Column(length = 100, unique = true)
    private String email;

    private Boolean ativo;

    public Cliente() {
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
