package sn.ept.git.seminaire.cicd.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.ExerciceVMTestData;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.mappers.vm.ExerciceVMMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciceVMMapperTest extends MapperBaseTest{

    static ExerciceVM vm;
    static Exercice entity;

    @Autowired
    private ExerciceVMMapper mapper;

    @BeforeAll
    static void beforeAll() {
        vm = ExerciceVMTestData.defaultVM();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(vm);

        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringFields("societe", "interventions") //same as previous line according to object initialization mechanism
                .isEqualTo(vm);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        entity = mapper.asEntity(vm);
        vm = mapper.asDTO(entity);
        assertThat(vm)
                .isNotNull()
                //.hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .isEqualTo(entity);
    }


}
