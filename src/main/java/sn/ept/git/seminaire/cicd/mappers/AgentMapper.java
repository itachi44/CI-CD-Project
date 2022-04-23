package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.AgentDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Agent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentMapper extends GenericMapper<Agent, AgentDTO> {

}