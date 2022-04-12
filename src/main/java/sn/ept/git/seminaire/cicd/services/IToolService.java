package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.ToolDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ToolVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IToolService extends GenericService<ToolDTO, ToolVM, UUID> {
}