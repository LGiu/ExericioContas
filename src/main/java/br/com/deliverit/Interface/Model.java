package br.com.deliverit.Interface;

import java.util.Date;

public interface Model {

    Long getId();

    void setDataCriacao(Date dataCriacao);

    void setDataAlteracao(Date dataAlteracao);

}
