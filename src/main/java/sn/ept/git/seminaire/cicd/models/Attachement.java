package sn.ept.git.seminaire.cicd.models;

import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="acicd_attachements")
@Where(clause = Type.CLAUSE)
public class Attachement extends BaseEntity{

    @NotBlank
    @Size(min = SizeMapping.Name.MIN,max = SizeMapping.Name.MAX)
    private  String name;

    @NotBlank
    @Size(min = SizeMapping.Description.MIN,max = SizeMapping.Description.MAX)
    private  String description;

    @Column(unique = true)
    @NotBlank
    @Size(min = SizeMapping.Location.MIN,max = SizeMapping.Location.MAX)
    private  String location;

    @NotBlank
    @Size(min = SizeMapping.Hash.MIN,max = SizeMapping.Hash.MAX)
    private  String hash;


    @Where(clause = BaseEntity.CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event  event;



}
