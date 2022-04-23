package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocieteMapper extends GenericMapper<Societe, SocieteDTO> {

}