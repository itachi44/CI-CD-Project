package sn.ept.git.seminaire.cicd.dto.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import sn.ept.git.seminaire.cicd.dto.base.AttachementBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachementVM extends AttachementBaseDTO {

    @JsonProperty("id_event")
    private UUID idEvent;


}