package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.AttachementDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Attachement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachementMapper extends GenericMapper<Attachement, AttachementDTO> {

}