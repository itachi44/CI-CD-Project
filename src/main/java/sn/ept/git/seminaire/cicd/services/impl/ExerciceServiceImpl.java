package sn.ept.git.seminaire.cicd.services.impl;

import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.ExerciceVMMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.ExerciceRepository;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import sn.ept.git.seminaire.cicd.services.IExerciceService;
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
public class ExerciceServiceImpl implements IExerciceService {

    private final ExerciceRepository repository;
    private final SocieteRepository societeRepository;
    private final ExerciceMapper mapper;
    private final ExerciceVMMapper vmMapper;

    public ExerciceServiceImpl(ExerciceRepository repository, SocieteRepository societeRepository, ExerciceMapper mapper, ExerciceVMMapper vmMapper) {
        this.repository = repository;
        this.societeRepository = societeRepository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ExerciceDTO save(ExerciceVM vm) {

        final Optional<Societe> societe = societeRepository.findById(vm.getIdSociete());
        ExceptionUtils.presentOrThrow(societe, ItemNotFoundException.SOCIETE_BY_ID, vm.getIdSociete().toString());

        Optional<Exercice> optional = repository.findByDates(vm.getStart(), vm.getEnd());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PLAGE_EXERCICE_EXIST, vm.getStart().toString(),vm.getEnd().toString());

        Exercice entity =  vmMapper.asEntity(vm);
        entity.setSociete(societe.get());

        return mapper.asDTO(repository.saveAndFlush(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(UUID uuid) {
        final Optional<Exercice> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.SITE_BY_ID, uuid.toString());
        final Exercice site = optional.get();
        site.setDeleted(true);
        repository.saveAndFlush(site);
    }

    @Override
    public Optional<ExerciceDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<ExerciceDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExerciceDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ExerciceDTO update(UUID uuid, ExerciceVM vm) {

        Optional<Exercice> optional = repository.findByDates(vm.getStart(), vm.getEnd());
        ExceptionUtils.absentOrThrow(optional, ItemExistsException.PLAGE_EXERCICE_EXIST, vm.getStart().toString(),vm.getEnd().toString());

        final Optional<Societe> societe = societeRepository.findById(vm.getIdSociete());
        ExceptionUtils.presentOrThrow(societe, ItemNotFoundException.SOCIETE_BY_ID, vm.getIdSociete().toString());

        optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.EXECICE_BY_ID, vm.getId().toString());

        final Exercice item = optional.get();
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
