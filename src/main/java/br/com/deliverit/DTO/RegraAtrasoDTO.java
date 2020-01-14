package br.com.deliverit.DTO;


import java.math.BigDecimal;

public class RegraAtrasoDTO<T>  {

    private Integer diasAtrasoMin;

    private Integer diasAtrasoMax;

    private BigDecimal multa;

    private BigDecimal jurosDia;

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
