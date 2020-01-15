package br.com.deliverit.Util;


import br.com.deliverit.Interface.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class ServiceGenerico<U extends Model> {

    private final JpaRepository<U, Long> jpaRepository;
    private final Class<U> uClass;

    @PersistenceContext
    private EntityManager entityManager;

    public ServiceGenerico(JpaRepository<U, Long> jpaRepository, Class<U> uClass) {
        this.jpaRepository = jpaRepository;
        this.uClass = uClass;
    }

    public Retorno<U> validador(U u) {
        Retorno<U> retorno = Validador.validacaoInicial(u);
        if (retorno.isErro()) {
            return retorno;
        }
        if (u.getId() != null && !existe(u)) {
            return new Retorno("O registro não existe!");
        }

        return retorno;
    }

    @Transactional
    public Retorno<U> exclui(Long id) {
        try {
            return exclui(buscaPorId(id));
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    public Retorno<U> exclui(U u) {
        try {
            if (u == null) {
                return new Retorno("Id informado não existe!");
            }
            jpaRepository.delete(u);
            return new Retorno(u.getId());
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    @Transactional
    public Retorno<U> salva(U u) {
        return salva(u, true);
    }

    @Transactional
    public Retorno<U> salva(U u, boolean valida) {
        if (valida) {
            Retorno<U> retorno = validador(u);
            if (retorno.isErro()) {
                return retorno;
            }
        }
        u = regra(u);
        jpaRepository.saveAndFlush(u);
        return new Retorno(u);
    }

    public U regra(U u) {
        return u;
    }

    public List<U> buscaLista() {
        try {
            return jpaRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public U buscaPorId(Long id) {
        try {
            if (id == null) {
                return null;
            }
            return (U) entityManager.find(uClass, id);
        } catch (Exception inored) {
            return null;
        }
    }

    public boolean existe(U u) {
        try {
            if (u == null || u.getId() == null) {
                return false;
            }

            return jpaRepository.existsById(u.getId());
        } catch (Exception e) {
            return false;
        }
    }

}
