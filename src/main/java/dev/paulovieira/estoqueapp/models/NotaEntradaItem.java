package dev.paulovieira.estoqueapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "nota_entrada_item")
public class NotaEntradaItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @NotNull(message = "Informe o produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "nota_entrada_id")
    @NotNull(message = "Informe a nota de entrada")
    private NotaEntrada notaEntrada;

    @NotNull(message = "Informe a quantidade")
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull(message = "Informe o valor unitário")
    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaEntrada getNotaEntrada() {
        return notaEntrada;
    }

    public void setNotaEntrada(NotaEntrada notaEntrada) {
        this.notaEntrada = notaEntrada;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
