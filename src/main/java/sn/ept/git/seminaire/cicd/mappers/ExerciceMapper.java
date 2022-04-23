package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciceMapper extends GenericMapper<Exercice, ExerciceDTO> {

}