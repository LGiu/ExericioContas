package br.com.deliverit.Model;


import br.com.deliverit.Interface.Model;
import br.com.deliverit.Util.Modelador;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "conta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conta extends Modelador<Conta> implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome deve ser informado!")
    @Size(max = 255, message = "O nome deve ser de no máximo 255 caracteres!")
    private String nome;

    @NotNull(message = "O valor deve ser informado!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(scale = 2, precision = 10)
    private BigDecimal valorOriginal;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(scale = 2, precision = 10)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal valorCorrigido;

    @Range(min = 0, message = "Tamanho deve estar ser no mínimo 0")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer diasAtraso;

    @NotNull(message = "A data de vencimento deve ser informada!")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataVencimento;

    @NotNull(message = "A data de pagamento deve ser informada!")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataPagamento;

    @Override
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

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorCorrigido() {
        return valorCorrigido;
    }

    public void setValorCorrigido(BigDecimal valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public Integer getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(Integer diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
