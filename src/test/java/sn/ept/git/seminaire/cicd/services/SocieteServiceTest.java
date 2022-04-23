package sn.ept.git.seminaire.cicd.services;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import sn.ept.git.seminaire.cicd.data.SocieteDTOTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.demo.exception.BadPhoneException;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.SocieteVMMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*@SqlGroup({
        @Sql("classpath:0_societe_data_test.sql"),
        @Sql("classpath:1_societe_data_test.sql"),
        @Sql("classpath:2_societe_data_test.sql"),
})*/
@Slf4j
class SocieteServiceTest extends ServiceBaseTest {

    @Autowired
    protected SocieteMapper mapper;
    @Autowired
    protected SocieteVMMapper vmMapper;
    @Autowired
    SocieteRepository societeRepository;
    @Autowired
    ISocieteService service;
    Optional<Societe> societe;
     static  SocieteVM vm ;
    SocieteDTO dto;


    @BeforeAll
    static void beforeAll(){
        log.info(" before all");
        vm = SocieteVMTestData.defaultVM();
    }

    @BeforeEach
     void beforeEach(){
       log.info(" before each");
    }

    @Test
    void save_shouldSaveSociete() {
        dto =service.save(vm);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void save_withSameName_shouldThrowException() {
        dto =service.save(vm);
        vm.setEmail(TestData.Update.email);
        vm.setPhone(TestData.Update.phone);
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }

    @Test
    void save_withSamePhone_shouldThrowException() {
        dto =service.save(vm);
        vm.setEmail(TestData.Update.email);
        vm.setName(TestData.Update.name);
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }

    @Test
    void save_withSameEmail_shouldThrowException() {
        dto =service.save(vm);
        vm.setPhone(TestData.Update.phone);
        vm.setName(TestData.Update.name);
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }

    @Test
    void findById_shouldReturnResult() {
        dto =service.save(vm);
        final Optional<SocieteDTO> optional = service.findById(dto.getId());
        assertThat(optional)
                .isNotNull()
                .isPresent()
                .get()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void findById_withBadId_ShouldReturnNoResult() {
        final Optional<SocieteDTO> optional = service.findById(UUID.randomUUID());
        assertThat(optional)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void delete_shouldDeleteSociete() {
        dto = service.save(vm);
        long oldCount = societeRepository.count();
        service.delete(dto.getId());
        long newCount = societeRepository.count();
        assertThat(oldCount).isEqualTo(newCount+1);
    }

    @Test
    void delete_withBadId_ShouldThrowException() {
        assertThrows(
                ItemNotFoundException.class,
                () ->service.delete(UUID.randomUUID())
        );
    }

/*findAll
    update*/


}