package sn.ept.git.seminaire.cicd.mappers.vm;

import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteVMMapper extends GenericMapper<Site, SiteVM> {

}