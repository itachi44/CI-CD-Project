package sn.ept.git.seminaire.cicd.mappers;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.mappers.generic.GenericMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteMapper extends GenericMapper<Site, SiteDTO> {

}