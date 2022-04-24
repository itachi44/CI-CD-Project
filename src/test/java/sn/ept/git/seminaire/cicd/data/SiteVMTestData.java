package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;

public final class SiteVMTestData extends TestData{

    public static SiteVM defaultVM(){
        return SiteVM
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .phone(Default.phone)
                .email(Default.email)
                .longitude(Default.longitude)
                .latitude(Default.latitude)
                .idSociete(Default.idSociete)
                .build();
    }

    public static SiteVM updatedVM() {
        return SiteVM
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .phone(Update.phone)
                .email(Update.email)
                .longitude(Update.longitude)
                .latitude(Update.latitude)
                .build();
    }
}
