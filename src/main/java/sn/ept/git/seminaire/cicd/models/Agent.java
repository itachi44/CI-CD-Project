package sn.ept.git.seminaire.cicd.models;

import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acicd_agents")
@Where(clause = Type.CLAUSE)
public class Agent extends BaseEntity {

    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MAX)
    private String firstName;

    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MAX)
    private String lastName;

    @Size(min = SizeMapping.Adresse.MIN, max = SizeMapping.Adresse.MAX)
    private String address;

    @Column(unique = true)
    @Size(min = SizeMapping.Phone.MIN, max = SizeMapping.Phone.MAX)
    private String phone;

    @Email
    @Column(unique = true)
    @Size(min = SizeMapping.Email.MIN, max = SizeMapping.Email.MAX)
    private String email;

    @Where(clause = CLAUSE)
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "mc_agents_sites",
            joinColumns = @JoinColumn(name = "id_agent"),
            inverseJoinColumns = @JoinColumn(name = "id_site")
    )
    private Set<Site> sites = new HashSet<>();

    @Where(clause = CLAUSE)
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "mc_agents_interventions_in",
            joinColumns = @JoinColumn(name = "id_agent"),
            inverseJoinColumns = @JoinColumn(name = "id_interventions")
    )
    private Set<Intervention> interventionsIn = new HashSet<>();

    @Where(clause = CLAUSE)
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "mc_agents_interventions_out",
            joinColumns = @JoinColumn(name = "id_agent"),
            inverseJoinColumns = @JoinColumn(name = "id_interventions")
    )
    private Set<Intervention> interventionsOut = new HashSet<>();


}
