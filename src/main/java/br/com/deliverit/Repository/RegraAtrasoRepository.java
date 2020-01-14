package br.com.deliverit.Repository;

import br.com.deliverit.Model.RegraAtraso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraAtrasoRepository extends JpaRepository<RegraAtraso, Long> {

    RegraAtraso findFirstByDiasAtrasoMinGreaterThanEqualAndDiasAtrasoMaxLessThanEqual(Integer dias1, Integer dias2);

}

