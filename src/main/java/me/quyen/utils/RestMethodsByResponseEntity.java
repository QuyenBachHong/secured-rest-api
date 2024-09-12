package me.quyen.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestMethodsByResponseEntity<R,ID> {
    ResponseEntity<List<R>> findAll();
    ResponseEntity<R> findById(ID entityId);
    ResponseEntity<?> createById(ID entityId);
    ResponseEntity<?> updateById(ID entityId, R entity);
    ResponseEntity<?> deleteById(ID entityId);
}
