package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.InterventionDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Intervention;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterventionMapper extends GenericMapper<Intervention, InterventionDTO> {

}