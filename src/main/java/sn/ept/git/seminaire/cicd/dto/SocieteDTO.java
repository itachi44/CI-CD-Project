package sn.ept.git.seminaire.cicd.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import sn.ept.git.seminaire.cicd.dto.base.SocieteBaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class SocieteDTO extends SocieteBaseDTO {

    //private Set<SiteDTO> sites;
    //private Set<SiteDTO> exercices;
}