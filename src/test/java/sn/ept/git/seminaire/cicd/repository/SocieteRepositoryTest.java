package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SocieteDTOTestData;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SocieteRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private SocieteMapper mapper;
    @Autowired
    private SocieteRepository repository;

    static SocieteDTO dto;
    Societe entity;
    Optional<Societe> optionalSociete;

    @BeforeAll
    static void beforeAll(){
        dto = SocieteDTOTestData.defaultDTO();
    }

    @BeforeEach
    void setUp() {
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);
    }

    @Test
    void FindByName_thenResult() {
        optionalSociete = repository.findByName(entity.getName());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByBadName_thenNotFound() {
        optionalSociete = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void FindDeleted_thenNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalSociete = repository.findByName(entity.getName());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void FindByEmail_thenResult() {
        optionalSociete = repository.findByEmail(entity.getEmail());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByBadEmail_thenNotFound() {
        optionalSociete = repository.findByEmail(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void FindByPhone_thenResult() {
        optionalSociete = repository.findByPhone(entity.getPhone());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByBadPhone_thenNotFound() {
        optionalSociete = repository.findByPhone(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByNameWithIdNotEqual_thenResult() {
        optionalSociete = repository.findByNameWithIdNotEqual(entity.getName(),UUID.randomUUID());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByNameWithIdNotEqual_withSameId_shouldReturnNoResult() {
        optionalSociete = repository.findByNameWithIdNotEqual(entity.getName(),entity.getId());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }




    @Test
    void findByPhoneWithIdNotEqual_thenResult() {
        optionalSociete = repository.findByPhoneWithIdNotEqual(entity.getPhone(),UUID.randomUUID());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByPhoneWithIdNotEqual_withSameId_shouldReturnNoResult() {
        optionalSociete = repository.findByPhoneWithIdNotEqual(entity.getPhone(),entity.getId());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByEmailWithIdNotEqual_thenResult() {
        optionalSociete = repository.findByEmailWithIdNotEqual(entity.getEmail(),UUID.randomUUID());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByEmailWithIdNotEqual_withSameId_shouldReturnNoResult() {
        optionalSociete = repository.findByEmailWithIdNotEqual(entity.getEmail(),entity.getId());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }

}