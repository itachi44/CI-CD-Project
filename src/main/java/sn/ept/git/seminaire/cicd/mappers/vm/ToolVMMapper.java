package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.ToolVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Tool;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToolVMMapper extends GenericMapper<Tool, ToolVM> {

}