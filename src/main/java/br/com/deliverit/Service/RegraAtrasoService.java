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
        try {
            RegraAtraso regraAtraso = regraAtrasoRepository.findFirstByDiasAtrasoMaxGreaterThanEqualAndDiasAtrasoMinLessThanEqual(dias, dias);
            BigDecimal novoValor = valor;
            novoValor = novoValor.add(valor.multiply(regraAtraso.getMulta().divide(new BigDecimal("100"))));
            novoValor = novoValor.add(valor.multiply(regraAtraso.getJurosDia().divide(new BigDecimal("100"))).multiply(new BigDecimal(dias)));
            return novoValor;
        } catch (Exception e) {
            return null;
        }
    }
}