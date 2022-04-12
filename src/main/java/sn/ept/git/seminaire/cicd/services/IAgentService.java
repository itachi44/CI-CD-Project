package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.AgentDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AgentVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IAgentService extends GenericService<AgentDTO, AgentVM, UUID> {
}