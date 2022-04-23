package sn.ept.git.seminaire.cicd.dto;

import sn.ept.git.seminaire.cicd.dto.base.AgentBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO extends AgentBaseDTO {

    private Set<SiteDTO> sites;
    //private Set<InterventionBaseDTO> interventionsIn;
    //private Set<InterventionBaseDTO> interventionsOut;

}