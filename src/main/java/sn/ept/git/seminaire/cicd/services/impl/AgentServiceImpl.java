package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.AgentDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AgentVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.AgentMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.AgentVMMapper;
import sn.ept.git.seminaire.cicd.models.Agent;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.repositories.AgentRepository;
import sn.ept.git.seminaire.cicd.repositories.SiteRepository;
import sn.ept.git.seminaire.cicd.services.IAgentService;
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
public class AgentServiceImpl implements IAgentService {

    private final AgentRepository repository;
    private final SiteRepository siteRepository;
    private final AgentMapper mapper;
    private final AgentVMMapper vmMapper;

    public AgentServiceImpl(AgentRepository repository, SiteRepository siteRepository, AgentMapper mapper, AgentVMMapper vmMapper) {
        this.repository = repository;
        this.siteRepository = siteRepository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional
    @Override
    public AgentDTO save(AgentVM vm) {
        Optional<Agent> optional = repository.findByPhone(vm.getPhone());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PHONE_EXISTS, vm.getPhone());

        optional = repository.findByEmail(vm.getEmail());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.EMAIL_EXISTS, vm.getEmail());

        //todo not found
        final List<Site> sites = siteRepository.findAllById(vm.getIdsSite());
         Agent item = vmMapper.asEntity(vm);
         item.setSites(sites.stream().collect(Collectors.toSet()));
        return mapper.asDTO(repository.saveAndFlush(item));
    }


    @Transactional
    @Override
    public void delete(UUID uuid) {
        final Optional<Agent> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, uuid.toString());
        final Agent site = optional.get();
        site.setDeleted(true);
        repository.saveAndFlush(site);
    }

    @Override
    public Optional<AgentDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<AgentDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AgentDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional
    @Override
    public AgentDTO update(UUID uuid, AgentVM vm) {

        Optional<Agent> optional = repository.findByPhoneWithIdDifferent(vm.getPhone(), uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PHONE_EXISTS, vm.getPhone());

        optional = repository.findByEmailWithIdDifferent(vm.getEmail(), uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.EMAIL_EXISTS, vm.getEmail());

        optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, vm.getId().toString());

        final Agent item = optional.get();
        item.setFirstName(vm.getFirstName());
        item.setLastName(vm.getLastName());
        item.setAddress(vm.getAddress());
        //more changes if required
        return mapper.asDTO(repository.saveAndFlush(item));
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
