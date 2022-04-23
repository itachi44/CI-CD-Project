package sn.ept.git.seminaire.cicd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="acicd_types")
@Where(clause = BaseEntity.CLAUSE)
public class Type extends BaseEntity{

    @NotBlank
    @Size(min = SizeMapping.Name.MIN,max = SizeMapping.Name.MAX)
    @Column(unique = true)
    private  String name;


    @NotBlank
    @Size(min = SizeMapping.Description.MIN,max = SizeMapping.Description.MAX)
    private  String description;


    @Where(clause = BaseEntity.CLAUSE)
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            mappedBy = "type",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Event> events = new HashSet<>();


}
