package me.quyen.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudMethods<R,ID> {
    ResponseEntity<List<R>> findAll();
    ResponseEntity<R> findById(ID entityId);
    ResponseEntity<?> create( R entity);
    ResponseEntity<?> update(ID entityId,R entity);
    <X> ResponseEntity<?> updatePartial(ID entityId,X entityProperty);
    <X,Y> ResponseEntity<?> updatePartial(ID entityId,X entityProp1,Y entityProp2);
    ResponseEntity<?> delete(ID entityId);
}
