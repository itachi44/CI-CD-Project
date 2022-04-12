package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.ToolDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Tool;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToolMapper extends GenericMapper<Tool, ToolDTO> {

}