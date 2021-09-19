package br.com.sicredi.assembly.core.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    T create(T t);
    Optional<T> get(String id);
    void edit(String id, T t);
    List<T> list();
    void delete(String id);
}
