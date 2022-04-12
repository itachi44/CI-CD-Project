package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.SiteMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.SiteVMMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SiteRepository;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import sn.ept.git.seminaire.cicd.services.ISiteService;
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
public class SiteServiceImpl implements ISiteService {

    private final SiteRepository repository;
    private final SocieteRepository societeRepository;
    private final SiteMapper mapper;
    private final SiteVMMapper vmMapper;

    public SiteServiceImpl(SiteRepository repository, SocieteRepository societeRepository, SiteMapper mapper, SiteVMMapper vmMapper) {
        this.repository = repository;
        this.societeRepository = societeRepository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SiteDTO save(SiteVM vm) {

        final Optional<Societe> societe = societeRepository.findById(vm.getIdSociete());
        ExceptionUtils.presentOrThrow(societe, ItemNotFoundException.SOCIETE_BY_ID, vm.getIdSociete().toString());

        final Optional<Site> optional = repository.findByName(vm.getName());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());

        Site entity =  vmMapper.asEntity(vm);
        entity.setSociete(societe.get());

        return mapper.asDTO(repository.saveAndFlush(entity));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(UUID uuid) {
        final Optional<Site> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, uuid.toString());
        final Site site = optional.get();
        site.setDeleted(true);
        repository.saveAndFlush(site);
    }

    @Override
    public Optional<SiteDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<SiteDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SiteDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SiteDTO update(UUID uuid, SiteVM vm) {

        Optional<Site> optional = repository.findByNameWithIdDifferent(vm.getName(), uuid);
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.NAME_EXISTS, vm.getName());

        final Optional<Societe> societe = societeRepository.findById(vm.getIdSociete());
        ExceptionUtils.presentOrThrow(societe, ItemNotFoundException.SOCIETE_BY_ID, vm.getIdSociete().toString());

        optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, vm.getId().toString());

        final Site item = optional.get();
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
