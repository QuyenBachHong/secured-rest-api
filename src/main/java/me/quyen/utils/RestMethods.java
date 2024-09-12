package me.quyen.utils;

import java.util.List;
import java.util.Optional;

public interface RestMethods<R,ID> {
    List<R> findAll();
    Optional<R> findById(ID entityId);
    void create(ID entityId);
    void update(ID entityId, R entity);
    void delete(ID entityId);
}
