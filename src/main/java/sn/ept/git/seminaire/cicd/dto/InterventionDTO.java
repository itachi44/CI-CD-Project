package sn.ept.git.seminaire.cicd.dto;

import sn.ept.git.seminaire.cicd.dto.base.InterventionBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterventionDTO extends InterventionBaseDTO {

    private SiteDTO site;
    private ExerciceDTO exercice;
    private Set<EventDTO> events;
    private Set<ToolDTO> tools;
    private Set<AgentDTO> agentsIn;
    private Set<AgentDTO> agentsOut;

}