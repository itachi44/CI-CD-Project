package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciceVMMapper extends GenericMapper<Exercice, ExerciceVM> {

}