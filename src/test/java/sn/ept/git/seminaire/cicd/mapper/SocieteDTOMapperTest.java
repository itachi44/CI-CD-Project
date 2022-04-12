package sn.ept.git.seminaire.cicd.mapper;

import sn.ept.git.seminaire.cicd.data.SocieteDTOTestData;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class SocieteDTOMapperTest extends  MapperBaseTest{

    SocieteDTO dto;
    Societe entity;

    @Autowired
    private SocieteMapper mapper;


    @BeforeEach
    void setUp() {
        dto = SocieteDTOTestData.defaultDTO();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(dto);
        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("sites", "exercices")
                .isEqualTo(dto);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        entity = mapper.asEntity(dto);
        dto =mapper.asDTO(entity);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes("^_")//just to discover
                .withEqualsForFields((idOne,idTwo)-> {
                    //use lambda
                    return  idOne instanceof String && idTwo.toString().equalsIgnoreCase(idOne.toString());
                },"id") //just to discover
                .isEqualTo(entity);

    }
}