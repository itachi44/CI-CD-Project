package sn.ept.git.seminaire.cicd.utils;

import sn.ept.git.seminaire.cicd.models.BaseEntity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ISENE
 */
public final class Utils {

    private Utils() {
        super();
    }

    public static boolean contains(Set<? extends BaseEntity> list, UUID id ) {
        return list.stream().filter(item->item.getId().equals(id)).findFirst().isPresent();
    }


    public static  Set<UUID> findAbsentIds(List<? extends BaseEntity> list, Set<UUID> ids ) {
        return findAbsentIds(list.stream().collect(Collectors.toSet()), ids);
    }

    public static  Set<UUID> findAbsentIds(Set<? extends BaseEntity> list, Set<UUID> ids ) {
        return ids
                .stream()
                .filter(item->!contains(list,item))
                .collect(Collectors.toSet());
    }


}