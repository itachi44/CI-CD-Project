package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.ToolDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ToolVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.ToolMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.ToolVMMapper;
import sn.ept.git.seminaire.cicd.models.Tool;
import sn.ept.git.seminaire.cicd.repositories.ToolRepository;
import sn.ept.git.seminaire.cicd.services.IToolService;
import sn.ept.git.seminaire.cicd.utils.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements IToolService {

    private final ToolRepository repository;
    private final ToolMapper mapper;
    private final ToolVMMapper vmMapper;

    public ToolServiceImpl(ToolRepository repository, ToolMapper mapper, ToolVMMapper vmMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ToolDTO save(ToolVM vm) {
        final Optional<Tool> optional = repository.findByName(vm.getName());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());
        return mapper.asDTO(repository.saveAndFlush(vmMapper.asEntity(vm)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(UUID uuid) {
        final Optional<Tool> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, uuid.toString());
        final Tool site = optional.get();
        site.setDeleted(true);
        repository.saveAndFlush(site);
    }

    @Override
    public Optional<ToolDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<ToolDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ToolDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ToolDTO update(UUID uuid, ToolVM vm) {
        final Optional<Tool> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, vm.getId().toString());
        final Tool item = optional.get();
        item.setName(vm.getName());
        //more changes if required
        return mapper.asDTO(repository.saveAndFlush(item));
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
