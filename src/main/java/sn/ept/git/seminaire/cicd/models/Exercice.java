package sn.ept.git.seminaire.cicd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import sn.ept.git.seminaire.cicd.enums.StatusExercice;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acicd_exercices")
@Where(clause = Type.CLAUSE)
public class Exercice extends BaseEntity {


    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MAX)
    private String name;

    @Builder.Default
    @NotNull
    @Column(name = "start_date", updatable = false)
    private Instant start = Instant.now();

    @Builder.Default
    @NotNull
    @Column(name = "end_date", updatable = false)
    private Instant end = Instant.now();

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusExercice status;


    @Where(clause = BaseEntity.CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_societe")
    private Societe societe;


    @Where(clause = BaseEntity.CLAUSE)
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            mappedBy = "exercice",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Intervention> interventions = new HashSet<>();


}
