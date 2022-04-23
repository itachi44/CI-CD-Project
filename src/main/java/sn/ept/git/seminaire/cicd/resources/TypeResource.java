package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.TypeDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TypeVM;
import sn.ept.git.seminaire.cicd.models.Type;
import sn.ept.git.seminaire.cicd.services.ITypeService;
import sn.ept.git.seminaire.cicd.utils.ResponseUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TypeResource {

    private final ITypeService service;

    public TypeResource(ITypeService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Type.ALL)
    public ResponseEntity<Page<TypeDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<TypeDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Type.FIND_BY_ID)
    public ResponseEntity<TypeDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Type.ADD)
    public ResponseEntity<TypeDTO> create(@RequestBody @Valid TypeVM vm) {
        TypeDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Type.DELETE)
    public ResponseEntity<Type> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Type.UPDATE)
    public ResponseEntity<TypeDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid TypeVM vm) {
        final TypeDTO dto = service.update(id, vm);
        Optional<TypeDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
