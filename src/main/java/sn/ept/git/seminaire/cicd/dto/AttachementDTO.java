package sn.ept.git.seminaire.cicd.dto;

import sn.ept.git.seminaire.cicd.dto.base.AttachementBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachementDTO extends AttachementBaseDTO {

    private EventDTO event;

}