package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;

public final class SocieteVMTestData extends TestData {

    public static SocieteVM defaultVM() {
        return SocieteVM
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .address(Default.address)
                .phone(Default.phone)
                .email(Default.email)
                .longitude(Default.longitude)
                .latitude(Default.latitude)
                .build();
    }

    public static SocieteVM updatedVM() {
        return SocieteVM
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .address(Update.address)
                .phone(Update.phone)
                .email(Update.email)
                .longitude(Update.longitude)
                .latitude(Update.latitude)
                .build();
    }
}
