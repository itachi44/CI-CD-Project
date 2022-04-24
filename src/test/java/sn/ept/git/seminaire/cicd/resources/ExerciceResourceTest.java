package sn.ept.git.seminaire.cicd.resources;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import sn.ept.git.seminaire.cicd.data.ExerciceVMTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.services.IExerciceService;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import sn.ept.git.seminaire.cicd.utils.TestUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class ExerciceResourceTest extends BasicResourceTest {

    static private ExerciceVM vm;
    @Autowired
    private IExerciceService service;
    private ExerciceDTO dto;

    @BeforeAll
    static void beforeAll() {
        log.info(" before all ");
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each ");
        service.deleteAll();
        vm = ExerciceVMTestData.defaultVM();
    }

    @Test
    void findAll_shouldReturnExercices() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Exercice.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print()) //can print request details
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].id").exists())
                .andExpect(jsonPath("$.content.[0].version").exists())
                .andExpect(jsonPath("$.content.[0].enabled").exists())
                .andExpect(jsonPath("$.content.[0].deleted").exists())
                .andExpect(jsonPath("$.content.[0].enabled", is(true)))
                .andExpect(jsonPath("$.content.[0].deleted").value(false))
                .andExpect(jsonPath("$.content.[0].name", is(dto.getName())))
                .andExpect(jsonPath("$.content.[0].status", is(dto.getStatus())))
                .andExpect(jsonPath("$.content.[0].start", is(dto.getStart())))
                .andExpect(jsonPath("$.content.[0].end", is(dto.getEnd())))
                .andExpect(jsonPath("$.content.[0].end", is(dto.getSociete())));


    }

    @Test
    void findById_shouldReturnExercice() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Exercice.FIND_BY_ID, dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.content.[0].status", is(dto.getStatus())))
                .andExpect(jsonPath("$.content.[0].start", is(dto.getStart())))
                .andExpect(jsonPath("$.content.[0].end", is(dto.getEnd())));
    }

    @Test
    void findById_withBadId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get(UrlMapping.Exercice.FIND_BY_ID, UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void add_shouldCreateExercice() throws Exception {
        mockMvc.perform(
                        post(UrlMapping.Exercice.ADD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.content.[0].status", is(vm.getStatus())))
                .andExpect(jsonPath("$.content.[0].start", is(vm.getStart())))
                .andExpect(jsonPath("$.content.[0].end", is(vm.getEnd())));
    }

    @Test
    void add_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(post(UrlMapping.Exercice.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(post(UrlMapping.Exercice.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_shouldUpdateSite() throws Exception {
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        vm.setStatus(TestData.Update.status);
        mockMvc.perform(
                        put(UrlMapping.Exercice.UPDATE, dto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.status").value(vm.getStatus()));
    }

    @Test
    void update_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(put(UrlMapping.Exercice.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(put(UrlMapping.Exercice.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_shouldDeleteExercice() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Exercice.DELETE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void delete_withBadId_shouldReturnNotFound() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Exercice.DELETE, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}
