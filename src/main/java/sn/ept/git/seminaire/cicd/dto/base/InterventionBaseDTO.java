package sn.ept.git.seminaire.cicd.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import sn.ept.git.seminaire.cicd.dto.BaseDTO;
import sn.ept.git.seminaire.cicd.enums.StatusIntervention;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterventionBaseDTO extends BaseDTO {

    @JsonProperty("start_date")
    @NotNull
    private Instant start;

    @JsonProperty("end_date")
    @NotNull
    private Instant end ;

    @JsonProperty("comment_in")
    @NotBlank
    @Size(min = SizeMapping.Comment.MIN, max = SizeMapping.Comment.MAX)
    private String commentIn;

    @JsonProperty("comment_out")
    @NotBlank
    @Size(min = SizeMapping.Comment.MIN, max = SizeMapping.Comment.MAX)
    private String commentOut;

    @NotNull
    private StatusIntervention status;

}