package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.InterventionDTO;
import sn.ept.git.seminaire.cicd.dto.vm.InterventionVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IInterventionService extends GenericService<InterventionDTO, InterventionVM, UUID> {

}