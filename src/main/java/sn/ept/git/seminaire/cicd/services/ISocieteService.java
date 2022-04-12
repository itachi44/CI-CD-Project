package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;
import java.util.UUID;


public interface ISocieteService extends GenericService<SocieteDTO, SocieteVM, UUID> {

}
