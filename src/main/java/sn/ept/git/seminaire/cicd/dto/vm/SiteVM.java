package sn.ept.git.seminaire.cicd.dto.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import sn.ept.git.seminaire.cicd.dto.base.SiteBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteVM extends SiteBaseDTO {

    @JsonProperty("id_societe")
    private UUID idSociete;

}