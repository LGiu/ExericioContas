package br.com.deliverit.Util;

import br.com.deliverit.Interface.Model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Validador {

    public static <U extends Model> Retorno validacaoInicial(U u) {
        if (u == null) {
            return new Retorno("Objeto nulo!");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<U>> violations = validator.validate(u);
        List<String> erros = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<U> erro : violations) {
                erros.add(u.getClass().getSimpleName() + "." + erro.getPropertyPath() + ":" + erro.getMessage());
            }
            return new Retorno(erros);
        }

        return new Retorno();
    }
}
