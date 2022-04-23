package sn.ept.git.seminaire.cicd.dto;

import sn.ept.git.seminaire.cicd.dto.base.ExerciceBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciceDTO extends ExerciceBaseDTO {

    private SocieteDTO societe;
   // private Set<Intervention> interventions;
}