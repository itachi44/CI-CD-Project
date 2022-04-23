package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.AgentDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AgentVM;
import sn.ept.git.seminaire.cicd.models.Agent;
import sn.ept.git.seminaire.cicd.services.IAgentService;
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
public class AgentResource {

    private final IAgentService service;

    public AgentResource(IAgentService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Agent.ALL)
    public ResponseEntity<Page<AgentDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<AgentDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Agent.FIND_BY_ID)
    public ResponseEntity<AgentDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Agent.ADD)
    public ResponseEntity<AgentDTO> create(@RequestBody @Valid AgentVM vm) {
        AgentDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Agent.DELETE)
    public ResponseEntity<Agent> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Agent.UPDATE)
    public ResponseEntity<AgentDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid  AgentVM vm) {
        final AgentDTO dto = service.update(id, vm);
        Optional<AgentDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
