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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="acicd_events")
@Where(clause = Event.CLAUSE)
public class Event extends BaseEntity{

    @NotBlank
    @Size(min = SizeMapping.Title.MIN,max = SizeMapping.Title.MAX)
    private  String title;

    @NotBlank
    @Size(min = SizeMapping.Description.MIN,max = SizeMapping.Description.MAX)
    private  String description;

    private float longitude;

    private float latitude;


    @Where(clause = CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_intervention")
    private Intervention intervention;


    @Where(clause = CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type  type;


    @Where(clause = CLAUSE)
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            mappedBy = "event",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Attachement> attachements = new HashSet<>();


}
