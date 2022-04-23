package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.InterventionVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Intervention;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterventionVMMapper extends GenericMapper<Intervention, InterventionVM> {

}