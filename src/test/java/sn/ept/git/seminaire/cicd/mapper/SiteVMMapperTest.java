package sn.ept.git.seminaire.cicd.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SiteVMTestData;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.mappers.vm.SiteVMMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import static org.assertj.core.api.Assertions.assertThat;

class SiteVMMapperTest extends MapperBaseTest{

    static SiteVM vm;
    static Site entity;

    @Autowired
    private SiteVMMapper mapper;

    @BeforeAll
    static void beforeAll() {
        vm = SiteVMTestData.defaultVM();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(vm);

        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringFields("society", "interventions", "agents") //same as previous line according to object initialization mechanism
                .isEqualTo(vm);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        entity = mapper.asEntity(vm);
        vm = mapper.asDTO(entity);
        assertThat(vm)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .isEqualTo(entity);
    }
}
