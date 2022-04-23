package sn.ept.git.seminaire.cicd.models;

import sn.ept.git.seminaire.cicd.enums.StatusIntervention;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acicd_interventions")
@Where(clause = BaseEntity.CLAUSE)
public class Intervention extends BaseEntity {


    @Builder.Default
    @NotNull
    @Column(name = "start_date", updatable = false)
    private Instant start = Instant.now();

    @Builder.Default
    @NotNull
    @Column(name = "end_date", updatable = false)
    private Instant end = Instant.now();

    @NotBlank
    @Size(min = SizeMapping.Comment.MIN, max = SizeMapping.Comment.MAX)
    private String commentIn;

    @NotBlank
    @Size(min = SizeMapping.Comment.MIN, max = SizeMapping.Comment.MAX)
    private String commentOut;


    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusIntervention status;


    @Where(clause = BaseEntity.CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_site")
    private Site site;

    @Where(clause = BaseEntity.CLAUSE)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_exercice")
    private Exercice exercice;

    @Where(clause = BaseEntity.CLAUSE)
    @ToString.Exclude
    @OneToMany(
            mappedBy = "intervention",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Event> events = new HashSet<>();

    @Where(clause = BaseEntity.CLAUSE)
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "mc_interventions_tools",
            joinColumns = @JoinColumn(name = "id_intervention"),
            inverseJoinColumns = @JoinColumn(name = "id_tool")
    )
    private Set<Tool> tools = new HashSet<>();

    @Where(clause = BaseEntity.CLAUSE)
    @ToString.Exclude
    @ManyToMany(mappedBy = "interventionsIn", fetch = FetchType.LAZY)
    private Set<Agent> agentsIn = new HashSet<>();

    @Where(clause = BaseEntity.CLAUSE)
    @ToString.Exclude
    @ManyToMany(mappedBy = "interventionsOut", fetch = FetchType.LAZY)
    private Set<Agent> agentsOut = new HashSet<>();

    public Intervention site(Site site){
        if(null!=site){
            setSite(site);
            site.setInterventions(Optional.ofNullable(site.getInterventions()).orElse(new HashSet<>()));
            site.getInterventions().add(this);
        }
        return this;
    }

    public Intervention exercice(Exercice exercice){
        if(null!=exercice){
            setExercice(exercice);
            exercice.setInterventions(Optional.ofNullable(exercice.getInterventions()).orElse(new HashSet<>()));
            exercice.getInterventions().add(this);
        }
        return this;
    }


    public Intervention agentsIn(List<Agent>  in){
        if(null!=in){
            setAgentsIn(Optional.ofNullable(getAgentsIn()).orElse(new HashSet<>()));
            getAgentsIn().addAll(in);
            in.stream().forEach(item->{
                item.setInterventionsIn(Optional.ofNullable(item.getInterventionsIn()).orElse(new HashSet<>()));
                item.getInterventionsIn().add(this);
            });
        }
        return this;
    }


    public Intervention agentsOut(List<Agent>  out){
        if(null!=out){
            setAgentsOut(Optional.ofNullable(getAgentsOut()).orElse(new HashSet<>()));
            getAgentsOut().addAll(out);
            out.stream().forEach(item->{
                item.setInterventionsOut(Optional.ofNullable(item.getInterventionsOut()).orElse(new HashSet<>()));
                item.getInterventionsOut().add(this);
            });
        }
        return this;
    }


    public Intervention tools( List<Tool> tools){
        if(null!=tools){
            setTools(Optional.ofNullable(getTools()).orElse(new HashSet<>()));
            getTools().addAll(tools);
            tools.stream().forEach(item->{
                item.setInterventions(Optional.ofNullable(item.getInterventions()).orElse(new HashSet<>()));
                item.getInterventions().add(this);
            });
        }
        return this;
    }

}
