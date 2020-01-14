package br.com.deliverit.Util;

import br.com.deliverit.Interface.Model;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;

public class Patch<T, U extends Model> {

    private final ServiceGenerico<U> serviceGenerico;
    private final Class<U> uClass;
    private final Conversor<U, T> conversor;

    public Patch(ServiceGenerico<U> serviceGenerico, Class<T> tClass, Class<U> uClass) {
        this.serviceGenerico = serviceGenerico;
        this.uClass = uClass;
        conversor = new Conversor<>(uClass, tClass);

    }

    public ResponseEntity<List<T>> consultar() {
        return new ResponseEntity<>(conversor.paraDTO(serviceGenerico.buscaLista()), HttpStatus.OK);
    }


    public ResponseEntity<T> consultar(Long id) {
        return new ResponseEntity<>(conversor.paraDTO(serviceGenerico.buscaPorId(id)), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> salva(U u) {
        u = preInitializy(u);
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> altera(U u) {
        u = aplicaAlteracoes(u, serviceGenerico.buscaPorId(u.getId()));
        if (u == null) {
            return new ResponseEntity<>(new Retorno("Id não foi informado ou não existe"), HttpStatus.OK);
        }
        u = preInitializy(u);
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> exclui(Long id) {
        return exclui(id, false);
    }

    public ResponseEntity<Retorno> exclui(Long id, boolean validaPermissao) {
        return new ResponseEntity<>(serviceGenerico.exclui(id), HttpStatus.OK);
    }

    private U aplicaAlteracoes(U u, U uAnt) {
        if (u != null && uAnt != null) {
            BeanWrapper beanWrapperAnt = new BeanWrapperImpl(uAnt);
            BeanWrapper beanWrapper = new BeanWrapperImpl(u);
            for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
                try {
                    if (!propertyDescriptor.getName().equals("id")) {
                        beanWrapperAnt.setPropertyValue(propertyDescriptor.getName(), beanWrapper.getPropertyValue(propertyDescriptor.getName()));
                    }
                } catch (Exception ignored) {
                }
            }
            return (U) beanWrapperAnt.getWrappedInstance();
        }
        return null;
    }

    public U preInitializy(U u) {
        if (u.getId() == null) {
            u.setDataCriacao(new Date());
        }
        u.setDataAlteracao(new Date());
        return u;
    }

}
