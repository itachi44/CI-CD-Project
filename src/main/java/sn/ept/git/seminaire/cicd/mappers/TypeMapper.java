package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.TypeDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper extends GenericMapper<Type, TypeDTO> {

}