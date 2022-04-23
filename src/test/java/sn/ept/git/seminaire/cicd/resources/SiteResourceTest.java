package sn.ept.git.seminaire.cicd.resources;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import sn.ept.git.seminaire.cicd.data.SiteVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.services.ISiteService;
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
class SiteResourceTest extends BasicResourceTest{

    static private SiteVM vm;
    @Autowired
    private ISiteService service;
    private SiteDTO dto;

    @BeforeAll
    static void beforeAll() {
        log.info(" before all ");
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each ");
        service.deleteAll();
        vm = SiteVMTestData.defaultVM();
    }

    @Test
    void findAll_shouldReturnSites() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Site.ALL)
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
                .andExpect(jsonPath("$.content.[0].phone").value(dto.getPhone()))
                .andExpect(jsonPath("$.content.[0].email").value(dto.getEmail()))
                .andExpect(jsonPath("$.content.[0].longitude").value(dto.getLongitude()))
                .andExpect(jsonPath("$.content.[0].latitude").value(dto.getLatitude()));

    }

    @Test
    void findById_shouldReturnSite() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(get(UrlMapping.Site.FIND_BY_ID, dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.phone").value(dto.getPhone()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()))
                .andExpect(jsonPath("$.longitude").value(dto.getLongitude()))
                .andExpect(jsonPath("$.latitude").value(dto.getLatitude()));
    }

    @Test
    void findById_withBadId_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get(UrlMapping.Site.FIND_BY_ID, UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void add_shouldCreateSite() throws Exception {
        mockMvc.perform(
                        post(UrlMapping.Site.ADD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.phone").value(vm.getPhone()))
                .andExpect(jsonPath("$.email").value(vm.getEmail()))
                .andExpect(jsonPath("$.longitude").value(vm.getLongitude()))
                .andExpect(jsonPath("$.latitude").value(vm.getLatitude()));
    }

    @Test
    void add_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withPhoneMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setPhone(RandomStringUtils.random(SizeMapping.Phone.MIN - 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withPhoneMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setPhone(RandomStringUtils.random(SizeMapping.Phone.MAX + 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withEmailMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setEmail(RandomStringUtils.random(SizeMapping.Email.MIN - 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withEmailMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setEmail(RandomStringUtils.random(SizeMapping.Email.MAX + 1));
        mockMvc.perform(post(UrlMapping.Site.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_shouldUpdateSite() throws Exception {
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        vm.setPhone(TestData.Update.phone);
        vm.setEmail(TestData.Update.email);
        vm.setLongitude(TestData.Update.longitude);
        vm.setLatitude(TestData.Update.latitude);
        mockMvc.perform(
                        put(UrlMapping.Site.UPDATE, dto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.phone").value(vm.getPhone()))
                .andExpect(jsonPath("$.email").value(vm.getEmail()))
                .andExpect(jsonPath("$.longitude").value(vm.getLongitude()))
                .andExpect(jsonPath("$.latitude").value(vm.getLatitude()));
    }

    @Test
    void update_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withPhoneMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setPhone(RandomStringUtils.random(SizeMapping.Phone.MIN - 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withPhoneMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setPhone(RandomStringUtils.random(SizeMapping.Phone.MAX + 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withEmailMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setEmail(RandomStringUtils.random(SizeMapping.Email.MIN - 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withEmailMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setEmail(RandomStringUtils.random(SizeMapping.Email.MAX + 1));
        mockMvc.perform(put(UrlMapping.Site.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_shouldDeleteSite() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Site.DELETE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void delete_withBadId_shouldReturnNotFound() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Site.DELETE, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

}
