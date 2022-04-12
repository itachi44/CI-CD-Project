package sn.ept.git.seminaire.cicd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ISENE
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemExistsException extends RuntimeException {

    public static final String DEFAUL_MESSAGE="Un des éléments que vous tentez d'jouter existe déjà ";
    public static final String NAME_EXISTS="Le nom  %s  existe déjà ";
    public static final String PHONE_EXISTS="Le numéro de téléphone  %s  existe déjà ";
    public static final String EMAIL_EXISTS="L'amail  %s  existe déjà ";
    public static final String PHONES_EXISTS="Les numéros de téléphone  %s  existent déjà ";
    public static final String PLAGE_EXERCICE_EXIST="Un exercice  existe déjà  dans la plae [%s,%s] ";
    public static final String NAMES_EXIST="Les noms  %s  existe déjà ";

    public ItemExistsException() {
        super(DEFAUL_MESSAGE);
    }
    public ItemExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ItemExistsException(String message) {
        super(message);
    }
    public ItemExistsException(Throwable cause) {
        super(cause);
    }

    public static String format(String template, String ...args) {
        return String.format(template,args);
    }
}
