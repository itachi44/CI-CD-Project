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
public class SocieteBaseDTO extends BaseDTO {


    @NotBlank
    @Size(min = SizeMapping.Name.MIN,max = SizeMapping.Name.MAX)
    private  String name;

    @Size(min = SizeMapping.Adresse.MIN,max = SizeMapping.Adresse.MAX)
    private  String address;

    @Size(min = SizeMapping.Phone.MIN,max = SizeMapping.Phone.MAX)
    private  String phone;
    @Size(min = SizeMapping.Email.MIN,max = SizeMapping.Email.MAX)
    private String email;
    private float longitude;
    private float latitude;

}