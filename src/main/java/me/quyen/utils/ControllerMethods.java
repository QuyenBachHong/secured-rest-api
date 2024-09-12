package me.quyen.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public interface ControllerMethods<R,ID> {
    @GetMapping("/____entities")
    ResponseEntity<List<R>> findAll();

    @GetMapping("/____entities/{id}")
    ResponseEntity<R> findById(@PathVariable("id") ID entityId);

    @PostMapping("/____entities")
    ResponseEntity<?> create(@RequestBody R entity);

    @PutMapping("/____entities/{id}")
    ResponseEntity<?> update(@PathVariable("id") ID entityId, @RequestBody R entity);

    @PatchMapping("/____entities/{id}/{____property}")
    <X> ResponseEntity<?> updatePartial(@PathVariable("id") ID entityId
            , @PathVariable("____property") X entityProperty);

    @PatchMapping("/____entities/{id}/{____prop1}/{____prop2}")
    <X,Y> ResponseEntity<?> updatePartial(@PathVariable("id") ID entityId
            , @PathVariable("____prop1") X entityProp1,@PathVariable("____prop2") Y entityProp2 );

    @DeleteMapping("/____entities/{id}")
    ResponseEntity<?> delete(@PathVariable("id") ID entityId);
}
