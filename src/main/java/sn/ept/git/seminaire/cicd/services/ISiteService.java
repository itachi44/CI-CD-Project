package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.UUID;

public interface ISiteService extends GenericService<SiteDTO, SiteVM, UUID> {
}