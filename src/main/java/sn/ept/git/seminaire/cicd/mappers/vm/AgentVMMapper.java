package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.AgentVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Agent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentVMMapper extends GenericMapper<Agent, AgentVM> {

}