package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.AttachementDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AttachementVM;
import sn.ept.git.seminaire.cicd.models.Attachement;
import sn.ept.git.seminaire.cicd.services.IAttachementService;
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
public class AttachementResource {

    private final IAttachementService service;

    public AttachementResource(IAttachementService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Attachement.ALL)
    public ResponseEntity<Page<AttachementDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<AttachementDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Attachement.FIND_BY_ID)
    public ResponseEntity<AttachementDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Attachement.ADD)
    public ResponseEntity<AttachementDTO> create(@RequestBody  @Valid AttachementVM vm) {
        AttachementDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Attachement.DELETE)
    public ResponseEntity<Attachement> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Attachement.UPDATE)
    public ResponseEntity<AttachementDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody  @Valid  AttachementVM vm) {
        final AttachementDTO dto = service.update(id, vm);
        Optional<AttachementDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
