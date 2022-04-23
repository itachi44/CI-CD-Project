package sn.ept.git.seminaire.cicd.dto.base;

import sn.ept.git.seminaire.cicd.dto.BaseDTO;
import sn.ept.git.seminaire.cicd.enums.StatusExercice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciceBaseDTO extends BaseDTO {
    private String name;
    private Instant start;
    private Instant end;
    private StatusExercice status;

}