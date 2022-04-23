package sn.ept.git.seminaire.cicd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ISENE
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException  extends RuntimeException {

    public static final String DEFAUL_MESSAGE="Impossible de retrouver l'élément recherché";
    public static final String SITE_BY_ID="Impossible de retrouver un site avec l'identifiant %s";
    public static final String TOOLS_BY_IDS="Impossible de retrouver les sites d'identifiants %s";
    public static final String AGENTS_BY_IDS="Impossible de retrouver les agents d'identifiants %s";
    public static final String  INTERVENTION_BY_ID="Impossible de retrouver une intervention avec l'identifiant %s";
    public static final String EXECICE_BY_ID="Impossible de retrouver un exercice avec l'identifiant %s";
    public static final String SOCIETE_BY_ID="Impossible de retrouver  une societe avec l'identifiant %s";

    public ItemNotFoundException() {
        super(DEFAUL_MESSAGE);
    }
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ItemNotFoundException(String message) {
        super(message);
    }
    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public static String format(String template, String ...args) {
        return String.format(template,args);
    }


    }
