package sn.ept.git.seminaire.cicd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@ToString
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String CLAUSE=" deleted = false ";
    //public static final String CLAUSE=" id is not null ";

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Builder.Default
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

    @Builder.Default
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate = Instant.now();

    @Version
    private int version;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean deleted = false;


}