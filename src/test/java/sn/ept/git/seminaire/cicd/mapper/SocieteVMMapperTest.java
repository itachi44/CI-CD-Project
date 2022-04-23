package sn.ept.git.seminaire.cicd.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.mappers.vm.SocieteVMMapper;
import sn.ept.git.seminaire.cicd.models.Societe;

import static org.assertj.core.api.Assertions.assertThat;

class SocieteVMMapperTest extends MapperBaseTest {

    static SocieteVM vm;
    static Societe entity;

    @Autowired
    private SocieteVMMapper mapper;

    @BeforeAll
    static void beforeAll() {
        vm = SocieteVMTestData.defaultVM();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(vm);

        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringFields("sites", "exercices") //same as previous line according to object initialization mechanism
                .isEqualTo(vm);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        entity = mapper.asEntity(vm);
        vm = mapper.asDTO(entity);
        assertThat(vm)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
}