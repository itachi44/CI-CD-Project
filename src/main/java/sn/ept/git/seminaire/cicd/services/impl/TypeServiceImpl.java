package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.TypeDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TypeVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.TypeMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.TypeVMMapper;
import sn.ept.git.seminaire.cicd.models.Type;
import sn.ept.git.seminaire.cicd.repositories.TypeRepository;
import sn.ept.git.seminaire.cicd.services.ITypeService;
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
public class TypeServiceImpl implements ITypeService {

    private final TypeRepository repository;
    private final TypeMapper mapper;
    private final TypeVMMapper vmMapper;

    public TypeServiceImpl(TypeRepository repository, TypeMapper mapper, TypeVMMapper vmMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public TypeDTO save(TypeVM vm) {
        final Optional<Type> optional = repository.findByName(vm.getName());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());
        return mapper.asDTO(repository.saveAndFlush(vmMapper.asEntity(vm)));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(UUID uuid) {
        final Optional<Type> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, uuid.toString());
        final Type site = optional.get();
        site.setDeleted(true);
        repository.saveAndFlush(site);
    }

    @Override
    public Optional<TypeDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<TypeDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TypeDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public TypeDTO update(UUID uuid, TypeVM vm) {
        final Optional<Type> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, vm.getId().toString());
        final Type item = optional.get();
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
