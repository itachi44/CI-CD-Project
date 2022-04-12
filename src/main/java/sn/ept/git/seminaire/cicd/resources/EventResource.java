package sn.ept.git.seminaire.cicd.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import sn.ept.git.seminaire.cicd.dto.EventDTO;
import sn.ept.git.seminaire.cicd.dto.vm.EventVM;
import sn.ept.git.seminaire.cicd.models.Event;
import sn.ept.git.seminaire.cicd.services.IEventService;
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
public class EventResource {

    private final IEventService service;

    public EventResource(IEventService service) {
        this.service = service;
    }

    @GetMapping(UrlMapping.Event.ALL)
    public ResponseEntity<Page<EventDTO>> findAll(
            @PageableDefault Pageable page
    ) {
        Page<EventDTO> result = service.findAll(page);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(UrlMapping.Event.FIND_BY_ID)
    public ResponseEntity<EventDTO> findById(@PathVariable ("id") UUID id) {
        return ResponseUtil.wrapOrNotFound(service.findById(id),HttpStatus.OK);
    }

    @PostMapping(UrlMapping.Event.ADD)
    public ResponseEntity<EventDTO> create(@RequestBody @Valid EventVM vm) {
        EventDTO created = service.save(vm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(UrlMapping.Event.DELETE)
    public ResponseEntity<Event> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(UrlMapping.Event.UPDATE)
    public ResponseEntity<EventDTO> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid  EventVM vm) {
        final EventDTO dto = service.update(id, vm);
        Optional<EventDTO> optional = Optional.ofNullable(dto);
        return ResponseUtil.wrapOrNotFound(optional,HttpStatus.ACCEPTED);
    }
}
