package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;


public final class ExerciceVMTestData extends TestData {

    public static ExerciceVM defaultVM(){
        return ExerciceVM
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .start(Default.start)
                .end(Default.end)
                .status(Default.status)
                .build();
    }

    public static ExerciceVM updatedVM(){
        return ExerciceVM
                .builder()
                .id(Update.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .status(Update.status)
                .build();
    }
}
