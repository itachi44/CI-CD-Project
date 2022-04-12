package sn.ept.git.seminaire.cicd.models;

import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="acicd_tools")
@Where(clause = BaseEntity.CLAUSE)
public class Tool extends BaseEntity{

    @NotBlank
    @Size(min = SizeMapping.Name.MIN,max = SizeMapping.Name.MAX)
    @Column(unique = true)
    private  String name;


    @NotBlank
    @Size(min = SizeMapping.Description.MIN,max = SizeMapping.Description.MAX)
    private  String description;


    @Where(clause = BaseEntity.CLAUSE)
    @ToString.Exclude
    @ManyToMany(mappedBy = "tools", fetch = FetchType.LAZY)
    private Set<Intervention> interventions = new HashSet<>();



}
