package sn.ept.git.seminaire.cicd.services.impl;

import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.dto.InterventionDTO;
import sn.ept.git.seminaire.cicd.dto.vm.InterventionVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.InterventionMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.InterventionVMMapper;
import sn.ept.git.seminaire.cicd.services.IInterventionService;
import sn.ept.git.seminaire.cicd.utils.ExceptionUtils;
import sn.ept.git.seminaire.cicd.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.ept.git.seminaire.cicd.models.*;
import sn.ept.git.seminaire.cicd.repositories.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterventionServiceImpl implements IInterventionService {


    private final InterventionRepository repository;
    private final AgentRepository agentRepository;
    private final SiteRepository siteRepository;
    private final ExerciceRepository exerciceRepository;
    private final ToolRepository toolRepository;
    private final InterventionMapper mapper;
    private final InterventionVMMapper vmMapper;

    public InterventionServiceImpl(InterventionRepository repository, AgentRepository agentRepository, SiteRepository siteRepository, ExerciceRepository exerciceRepository, ToolRepository toolRepository, InterventionMapper mapper, InterventionVMMapper vmMapper) {
        this.repository = repository;
        this.agentRepository = agentRepository;
        this.siteRepository = siteRepository;
        this.exerciceRepository = exerciceRepository;
        this.toolRepository = toolRepository;
        this.mapper = mapper;
        this.vmMapper = vmMapper;
    }

    @Override
    public InterventionDTO save(InterventionVM vm) {
        final Optional<Site> optionalSite = siteRepository.findById(vm.getIdSite());
        ExceptionUtils.presentOrThrow(optionalSite, ItemNotFoundException.SITE_BY_ID, vm.getIdSite().toString());

        final Optional<Exercice> optionalExercice = exerciceRepository.findById(vm.getIdExercice());
        ExceptionUtils.presentOrThrow(optionalExercice, ItemNotFoundException.EXECICE_BY_ID, vm.getIdExercice().toString());

        final List<Tool> tools = toolRepository.findAllById(vm.getIdsTools());
        final Set<UUID> toolsAbsentIds = Utils.findAbsentIds(tools, vm.getIdsTools());
        ExceptionUtils.emptyOrThrow(toolsAbsentIds, ItemNotFoundException.AGENTS_BY_IDS);


        final List<Agent> agentsIn = agentRepository.findAllById(vm.getIdsAgentsIn());
        final Set<UUID> agentsInAbsentIds = Utils.findAbsentIds(agentsIn, vm.getIdsAgentsIn());
        ExceptionUtils.emptyOrThrow(agentsInAbsentIds, ItemNotFoundException.TOOLS_BY_IDS);

        final List<Agent> agentsOut = agentRepository.findAllById(vm.getIdsAgentsOut());
        final Set<UUID> agentsOutAbsentIds = Utils.findAbsentIds(agentsOut, vm.getIdsAgentsOut());
        ExceptionUtils.emptyOrThrow(agentsOutAbsentIds, ItemNotFoundException.TOOLS_BY_IDS);

        Intervention item = vmMapper.asEntity(vm);
        item
                .site(optionalSite.get())
                .exercice(optionalExercice.get())
                .tools(tools)
                .agentsIn(agentsIn)
                .agentsOut(agentsOut);

        return mapper.asDTO(repository.saveAndFlush(item));
    }

    @Override
    public void delete(UUID uuid) {
        final Optional<Intervention> optional = repository.findById(uuid);
        ExceptionUtils.presentOrThrow(optional, ItemNotFoundException.INTERVENTION_BY_ID, uuid.toString());
        final Intervention item = optional.get();
        item.setDeleted(true);
        repository.saveAndFlush(item);
    }

    @Override
    public Optional<InterventionDTO> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .map(mapper::asDTO);
    }

    @Override
    public List<InterventionDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<InterventionDTO> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::asDTO);
    }

    @Override
    public InterventionDTO update(UUID uuid, InterventionVM vm) {
        return null;
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
