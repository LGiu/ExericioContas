package br.com.deliverit.Util;

import br.com.deliverit.Interface.Model;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor<U extends Model, T> {

    private final ObjectMapper objectMapper;
    private final Class<U> uClass;
    private final Class<T> tClass;

    public Conversor(Class<U> uClass, Class<T> tClass) {
        this.uClass = uClass;
        this.tClass = tClass;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public List<T> paraDTO(List<U> us) {
        return us.stream().map(this::paraDTO).collect(Collectors.toList());
    }

    public T paraDTO(U u) {
        return objectMapper.convertValue(u, tClass);
    }
}
