package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;


public final class ExerciceDTOTestData extends TestData{

    public static ExerciceDTO defaultDTO(){
        return ExerciceDTO
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
                .societe(Default.societe)
                .build();
    }

    public static ExerciceDTO updatedDTO(){
        return ExerciceDTO
                .builder()
                .id(Default.id)
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
