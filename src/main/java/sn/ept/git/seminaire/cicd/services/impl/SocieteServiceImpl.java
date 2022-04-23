package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.SocieteVMMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import sn.ept.git.seminaire.cicd.services.ISocieteService;
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
public class SocieteServiceImpl implements ISocieteService {

    private final SocieteRepository repository;
    private final SocieteMapper mapper;
    private final SocieteVMMapper vmMapper;

    public SocieteServiceImpl(SocieteRepository repository, SocieteMapper mapper, SocieteVMMapper vmMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional
    @Override
    public SocieteDTO save(SocieteVM vm) {
         Optional<Societe> optional = repository.findByName(vm.getName());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());

        optional = repository.findByPhone(vm.getPhone());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PHONE_EXISTS, vm.getPhone());

        optional = repository.findByEmail(vm.getEmail());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.EMAIL_EXISTS, vm.getEmail());

        return mapper.asDTO(repository.saveAndFlush(vmMapper.asEntity(vm)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(UUID uuid) {
        final Optional<Societe> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SOCIETE_BY_ID, uuid.toString());

        if(optional.isPresent()) {
            final Societe societe = optional.get();
            societe.setDeleted(true);
            repository.saveAndFlush(societe);
        }
    }

    @Override
    public Optional<SocieteDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<SocieteDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SocieteDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional
    @Override
    public SocieteDTO update(UUID uuid, SocieteVM vm) {
         Optional<Societe>  optional = repository.findByNameWithIdNotEqual(vm.getName(),uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());

        optional = repository.findByPhoneWithIdNotEqual(vm.getPhone(),uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PHONE_EXISTS, vm.getPhone());

        optional = repository.findByEmailWithIdNotEqual(vm.getEmail(),uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.EMAIL_EXISTS, vm.getEmail());

        optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SOCIETE_BY_ID, vm.getId().toString());
        Societe item= new Societe();
        if(optional.isPresent()){
            item = optional.get();
        }
        item.setName(vm.getName());
        item.setPhone(vm.getPhone());
        item.setEmail(vm.getEmail());
        item.setAddress(vm.getAddress());
        item.setLongitude(vm.getLongitude());
        item.setLatitude(vm.getLatitude());

        return mapper.asDTO(repository.saveAndFlush(item));
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
