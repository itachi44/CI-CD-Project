package sn.ept.git.seminaire.cicd.dto.base;

import sn.ept.git.seminaire.cicd.dto.BaseDTO;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteBaseDTO extends BaseDTO {

    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MIN)
    private String name;
    @NotBlank
    @Size(min = SizeMapping.Phone.MIN, max = SizeMapping.Phone.MIN)
    private String phone;
    @NotBlank
    @Size(min = SizeMapping.Email.MIN, max = SizeMapping.Email.MIN)
    private String email;
    private float longitude;
    private float latitude;

}