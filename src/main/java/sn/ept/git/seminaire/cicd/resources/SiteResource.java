package sn.ept.git.seminaire.cicd.resources;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.services.ISiteService;
import sn.ept.git.seminaire.cicd.utils.ResponseUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SiteResource {

    private final ISiteService service;

    public SiteResource(ISiteService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Site.ALL)
    public ResponseEntity<Page<SiteDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<SiteDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Site.FIND_BY_ID)
    public ResponseEntity<SiteDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Site.ADD)
    public ResponseEntity<SiteDTO> create(@RequestBody @Valid SiteVM vm) {
        SiteDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Site.DELETE)
    public ResponseEntity<Site> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Site.UPDATE)
    public ResponseEntity<SiteDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid SiteVM vm) {
        final SiteDTO dto = service.update(id, vm);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dto),HttpStatus.ACCEPTED);
    }
}
