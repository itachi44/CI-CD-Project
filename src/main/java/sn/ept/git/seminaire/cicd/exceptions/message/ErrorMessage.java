package sn.ept.git.seminaire.cicd.exceptions.message;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 *
 * @author ISENE
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
public class ErrorMessage {
    private int status;
    private Date timestamp;
    private String message;
    private String description;

}
