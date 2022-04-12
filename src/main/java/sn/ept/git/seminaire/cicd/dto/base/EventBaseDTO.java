package sn.ept.git.seminaire.cicd.dto.base;

import sn.ept.git.seminaire.cicd.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBaseDTO extends BaseDTO {
    private String title;
    private String description;
    private float longitude;
    private float latitude;

}