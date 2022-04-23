package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.InterventionDTO;
import sn.ept.git.seminaire.cicd.dto.vm.InterventionVM;
import sn.ept.git.seminaire.cicd.models.Intervention;
import sn.ept.git.seminaire.cicd.services.IInterventionService;
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
public class InterventionResource {

    private final IInterventionService service;

    public InterventionResource(IInterventionService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Intervention.ALL)
    public ResponseEntity<Page<InterventionDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<InterventionDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Intervention.FIND_BY_ID)
    public ResponseEntity<InterventionDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Intervention.ADD)
    public ResponseEntity<InterventionDTO> create(@RequestBody @Valid InterventionVM vm) {
        InterventionDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Intervention.DELETE)
    public ResponseEntity<Intervention> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Intervention.UPDATE)
    public ResponseEntity<InterventionDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid InterventionVM vm) {
        final InterventionDTO dto = service.update(id, vm);
        Optional<InterventionDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
