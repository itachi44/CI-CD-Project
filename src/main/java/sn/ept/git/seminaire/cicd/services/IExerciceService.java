package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface IExerciceService extends GenericService<ExerciceDTO, ExerciceVM, UUID> {
}