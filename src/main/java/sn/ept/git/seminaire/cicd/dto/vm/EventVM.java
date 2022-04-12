package sn.ept.git.seminaire.cicd.dto.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import sn.ept.git.seminaire.cicd.dto.base.EventBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventVM extends EventBaseDTO {

    @JsonProperty("id_type")
    private UUID idType;

    @JsonProperty("id_intervention")
    private UUID idIntervention;


}