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
@Table(name = "acicd_sites")
@Where(clause = BaseEntity.CLAUSE)
public class Site extends BaseEntity {

    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MAX)
    @Column(unique = true)
    private String name;

    @Size(min = SizeMapping.Phone.MIN, max = SizeMapping.Phone.MAX)
    private String phone;

    @Size(min = SizeMapping.Email.MIN, max = SizeMapping.Email.MAX)
    private String email;

    private float longitude;

    private float latitude;

    @Where(clause = CLAUSE)
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_societe")
    private Societe societe;

    @Where(clause = CLAUSE)
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            mappedBy = "site",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Intervention> interventions = new HashSet<>();

    @Where(clause = CLAUSE)
    @ToString.Exclude
    @ManyToMany(mappedBy = "sites", fetch = FetchType.LAZY)
    private Set<Agent> agents = new HashSet<>();

}
