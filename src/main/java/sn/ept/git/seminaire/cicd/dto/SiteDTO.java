package sn.ept.git.seminaire.cicd.dto;

import sn.ept.git.seminaire.cicd.dto.base.SiteBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO extends SiteBaseDTO {

    private SocieteDTO societe;

    //private Set<InterventionDTO> interventions;
    //private Set<AgentDTO> agents;

}