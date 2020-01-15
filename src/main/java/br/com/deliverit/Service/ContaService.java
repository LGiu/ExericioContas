package br.com.deliverit.Service;


import br.com.deliverit.Model.Conta;
import br.com.deliverit.Repository.ContaRepository;
import br.com.deliverit.Util.Datas;
import br.com.deliverit.Util.Retorno;
import br.com.deliverit.Util.ServiceGenerico;
import br.com.deliverit.Util.Validador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService extends ServiceGenerico<Conta> {

    private final RegraAtrasoService regraAtrasoService;

    @Autowired
    public ContaService(ContaRepository contaRepository, RegraAtrasoService regraAtrasoService) {
        super(contaRepository, Conta.class);
        this.regraAtrasoService = regraAtrasoService;
    }

    @Override
    public Retorno<Conta> validador(Conta conta) {
        Retorno<Conta> retorno = Validador.validacaoInicial(conta);
        if (retorno.isErro()) {
            return retorno;
        }

        if(conta.getValorOriginal().compareTo(BigDecimal.ZERO) <= 0){
            return new Retorno<>("O valor deve ser maior ou igual a zero!");
        }

        return retorno;
    }


    @Override
    public Conta regra(Conta conta) {
        conta = validaAtraso(conta);
        return conta;
    }

    public Conta validaAtraso(Conta conta) {
        if (conta.getDataPagamento().after(conta.getDataVencimento())) {
            conta = calculaDiasAtraso(conta);
            conta = calculaValor(conta);
        } else {
            conta.setValorCorrigido(null);
            conta.setDiasAtraso(null);
        }
        return conta;
    }

    public Conta calculaDiasAtraso(Conta conta) {
        Integer diasEmAtraso = Datas.diffInDays(conta.getDataVencimento(), conta.getDataPagamento());
        conta.setDiasAtraso(diasEmAtraso);
        return conta;
    }

    public Conta calculaValor(Conta conta) {
        BigDecimal valorCorrigido = regraAtrasoService.aplicaRegraValor(conta.getDiasAtraso(), conta.getValorOriginal());
        if (valorCorrigido != null) {
            conta.setValorCorrigido(valorCorrigido);
        } else {
            conta.setValorCorrigido(conta.getValorOriginal());
        }
        return conta;
    }
}
