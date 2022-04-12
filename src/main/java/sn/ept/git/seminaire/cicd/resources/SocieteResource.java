package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.services.ISocieteService;
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
public class SocieteResource {

    private final ISocieteService service;

    public SocieteResource(ISocieteService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Societe.ALL)
    public ResponseEntity<Page<SocieteDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<SocieteDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Societe.FIND_BY_ID)
    public ResponseEntity<SocieteDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Societe.ADD)
    public ResponseEntity<SocieteDTO> create(@Valid @RequestBody  SocieteVM vm) {
        SocieteDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Societe.DELETE)
    public ResponseEntity<Societe> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Societe.UPDATE)
    public ResponseEntity<SocieteDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid SocieteVM vm) {
        final SocieteDTO dto = service.update(id, vm);
        Optional<SocieteDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
