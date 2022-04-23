package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.services.IExerciceService;
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
public class ExerciceResource {

    private final IExerciceService service;

    public ExerciceResource(IExerciceService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Exercice.ALL)
    public ResponseEntity<Page<ExerciceDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<ExerciceDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Exercice.FIND_BY_ID)
    public ResponseEntity<ExerciceDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Exercice.ADD)
    public ResponseEntity<ExerciceDTO> create(@RequestBody @Valid ExerciceVM vm) {
        ExerciceDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Exercice.DELETE)
    public ResponseEntity<Exercice> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Exercice.UPDATE)
    public ResponseEntity<ExerciceDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ExerciceVM vm) {
        final ExerciceDTO dto = service.update(id, vm);
        Optional<ExerciceDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
