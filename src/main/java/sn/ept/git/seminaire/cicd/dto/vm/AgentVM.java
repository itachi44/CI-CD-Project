package sn.ept.git.seminaire.cicd.dto.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import sn.ept.git.seminaire.cicd.dto.base.AgentBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentVM extends AgentBaseDTO {

    @JsonProperty("ids_site")
    private Set<UUID> idsSite;

}