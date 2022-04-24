package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.ExerciceDTOTestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.repositories.ExerciceRepository;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciceRepositoryTest extends RepositoryBaseTest{

    @Autowired
    private ExerciceMapper mapper;
    @Autowired
    private ExerciceRepository repository;

    static ExerciceDTO dto;
    Exercice entity;
    Optional<Exercice> optionalExercice;

    @BeforeAll
    static void beforeAll(){
        dto = ExerciceDTOTestData.defaultDTO();
    }

    @BeforeEach
    void setUp() {
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);
    }

    @Test
    void FindByDates_thenResult() {
        optionalExercice = repository.findByDates(entity.getStart(), entity.getEnd());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByBadDates_thenNotFound() {
        optionalExercice = repository.findByDates(Instant.now(), Instant.now());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void FindDeleted_thenNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalExercice = repository.findByDates(entity.getStart(), entity.getEnd());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }


}
