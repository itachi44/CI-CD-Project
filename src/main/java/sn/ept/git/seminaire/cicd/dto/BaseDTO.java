package sn.ept.git.seminaire.cicd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@ToString
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public  class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value ="id")
    private UUID id;

    @JsonProperty(value ="created_date")
    @Builder.Default
    private Instant createdDate = Instant.now();

    @JsonProperty(value ="last_modified_date")
    @Builder.Default
    private Instant lastModifiedDate = Instant.now();

    private int version;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean deleted = false;


}