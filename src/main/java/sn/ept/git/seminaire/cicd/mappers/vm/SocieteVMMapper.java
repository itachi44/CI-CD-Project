package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocieteVMMapper extends GenericMapper<Societe, SocieteVM> {

}