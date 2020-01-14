package br.com.deliverit.Service;


import br.com.deliverit.Model.RegraAtraso;
import br.com.deliverit.Repository.RegraAtrasoRepository;
import br.com.deliverit.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegraAtrasoService extends ServiceGenerico<RegraAtraso> {

    private final RegraAtrasoRepository regraAtrasoRepository;

    @Autowired
    public RegraAtrasoService(RegraAtrasoRepository regraAtrasoRepository) {
        super(regraAtrasoRepository, RegraAtraso.class);
        this.regraAtrasoRepository = regraAtrasoRepository;
    }

    public BigDecimal aplicaRegraValor(Integer dias, BigDecimal valor) {
        RegraAtraso regraAtraso = regraAtrasoRepository.findFirstByDiasAtrasoMinGreaterThanEqualAndDiasAtrasoMaxLessThanEqual(dias, dias);
        valor = valor.multiply(regraAtraso.getMulta().divide(new BigDecimal("100"))).multiply(regraAtraso.getJurosDia());
        valor = valor.multiply(regraAtraso.getJurosDia().multiply(new BigDecimal(dias)).divide(new BigDecimal("100"))).multiply(regraAtraso.getJurosDia());
        return valor;
    }
}
