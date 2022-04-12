package sn.ept.git.seminaire.cicd.services.impl;

import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.dto.EventDTO;
import sn.ept.git.seminaire.cicd.dto.vm.EventVM;
import sn.ept.git.seminaire.cicd.mappers.EventMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.EventVMMapper;
import sn.ept.git.seminaire.cicd.repositories.EventRepository;
import sn.ept.git.seminaire.cicd.services.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {


    private final EventRepository repository;
    private final EventMapper mapper;
    private final EventVMMapper vmMapper;

    public EventServiceImpl(EventRepository repository, EventMapper mapper, EventVMMapper vmMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Override
    public EventDTO save(EventVM vm) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<EventDTO> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<EventDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EventDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Override
    public EventDTO update(UUID uuid, EventVM vm) {
        return null;
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
