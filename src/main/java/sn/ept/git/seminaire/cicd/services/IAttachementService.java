package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.AttachementDTO;
import sn.ept.git.seminaire.cicd.dto.vm.AttachementVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IAttachementService extends GenericService<AttachementDTO, AttachementVM, UUID> {
}