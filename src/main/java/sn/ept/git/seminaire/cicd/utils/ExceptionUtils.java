package sn.ept.git.seminaire.cicd.utils;

import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author ISENE
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        super();
    }

    public static final void presentOrThrow(Optional optional, String template, String ... params){
        if(!optional.isPresent()){
            throw new ItemNotFoundException(
                    ItemNotFoundException.format(template, params)
            );
        }
    }


    public static final void emptyOrThrow(Set<UUID> ids, String template){
        if(!ids.isEmpty()){
            throw new ItemNotFoundException(
                    ItemNotFoundException.format(template, ids.stream().map(UUID::toString).reduce("",(a,b)->a.concat(",").concat(b)))
            );
        }
    }

    public static final void absentOrThrow(Optional optional, String template, String ... params){
        if(optional.isPresent()){
            throw new ItemExistsException(
                    ItemExistsException.format(template, params)
            );
        }
    }

}