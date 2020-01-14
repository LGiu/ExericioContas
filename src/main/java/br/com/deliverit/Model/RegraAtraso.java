package br.com.deliverit.Model;


import br.com.deliverit.Interface.Model;
import br.com.deliverit.Util.Modelador;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "regra_atraso", uniqueConstraints = {@UniqueConstraint(columnNames = {"diasAtrasoMin", "diasAtrasoMax"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegraAtraso extends Modelador<RegraAtraso> implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 0, max = 99999, message = "Tamanho deve estar ser no mínimo 0")
    private Integer diasAtrasoMin;

    @Range(min = 0, max = 99999, message = "Tamanho deve estar ser no mínimo 0")
    private Integer diasAtrasoMax;

    @NotNull(message = "A multa deve ser informada!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(scale = 2, precision = 10)
    private BigDecimal multa;

    @NotNull(message = "O juros deve ser informado!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(scale = 2, precision = 10)
    private BigDecimal jurosDia;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiasAtrasoMin() {
        return diasAtrasoMin;
    }

    public void setDiasAtrasoMin(Integer diasAtrasoMin) {
        this.diasAtrasoMin = diasAtrasoMin;
    }

    public Integer getDiasAtrasoMax() {
        return diasAtrasoMax;
    }

    public void setDiasAtrasoMax(Integer diasAtrasoMax) {
        this.diasAtrasoMax = diasAtrasoMax;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public BigDecimal getJurosDia() {
        return jurosDia;
    }

    public void setJurosDia(BigDecimal jurosDia) {
        this.jurosDia = jurosDia;
    }
}
