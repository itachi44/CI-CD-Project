package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.TypeDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TypeVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface ITypeService extends GenericService<TypeDTO, TypeVM, UUID> {
}