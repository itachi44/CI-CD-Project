package sn.ept.git.seminaire.cicd.services.impl;

import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.dto.AttachementDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AttachementVM;
import sn.ept.git.seminaire.cicd.mappers.AttachementMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.AttachementVMMapper;
import sn.ept.git.seminaire.cicd.repositories.AttachementRepository;
import sn.ept.git.seminaire.cicd.services.IAttachementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttachementServiceImpl implements IAttachementService {

    private final AttachementRepository repository;
    private final AttachementMapper mapper;
    private final AttachementVMMapper vmMapper;

    public AttachementServiceImpl(AttachementRepository repository, AttachementMapper mapper, AttachementVMMapper vmMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Override
    public AttachementDTO save(AttachementVM vm) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<AttachementDTO> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<AttachementDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AttachementDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Override
    public AttachementDTO update(UUID uuid, AttachementVM vm) {
        return null;
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
