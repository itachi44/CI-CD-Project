package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.ToolDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ToolVM;
import sn.ept.git.seminaire.cicd.models.Tool;
import sn.ept.git.seminaire.cicd.services.IToolService;
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
public class ToolResource {

    private final IToolService service;

    public ToolResource(IToolService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Tool.ALL)
    public ResponseEntity<Page<ToolDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<ToolDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Tool.FIND_BY_ID)
    public ResponseEntity<ToolDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Tool.ADD)
    public ResponseEntity<ToolDTO> create(@RequestBody @Valid ToolVM vm) {
        ToolDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Tool.DELETE)
    public ResponseEntity<Tool> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Tool.UPDATE)
    public ResponseEntity<ToolDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ToolVM vm) {
        final ToolDTO dto = service.update(id, vm);
        Optional<ToolDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
