package br.com.deliverit;

import br.com.deliverit.Model.Conta;
import br.com.deliverit.Service.ContaService;
import br.com.deliverit.Util.Datas;
import br.com.deliverit.Util.Retorno;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaServiceTest {

    @Autowired
    private ContaService contaService;

    @Test
    public void teste1() {
        Conta conta = new Conta();
        conta.setNome("Boleto");
        conta.setValorOriginal(new BigDecimal("100"));
        conta.setDataVencimento(Datas.stringParaDate("2019-01-14", "yyyy-MM-dd"));
        conta.setDataPagamento(Datas.stringParaDate("2019-01-18", "yyyy-MM-dd"));
        Retorno<Conta> retorno = contaService.salva(conta);
        assertNotNull(retorno.getIdGravado());
    }

    @Test
    public void teste2() {
        List<Conta> contaList = contaService.buscaLista();
        assertThat(contaList.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void teste3() {
        Conta conta = contaService.buscaLista().get(0);
        conta.setNome("Boleto Alterado");
        Retorno<Conta> retorno = contaService.salva(conta);
        assertNotNull(retorno.getIdGravado());
    }

    @Test
    public void teste4() {
        Conta conta = contaService.buscaLista().get(0);
        Retorno<Conta> retorno = contaService.exclui(conta);
        assertNotNull(retorno.getIdExcluido());
    }

}