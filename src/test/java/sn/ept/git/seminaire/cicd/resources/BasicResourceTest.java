package sn.ept.git.seminaire.cicd.resources;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.TrackingApplication;


@SpringBootTest(classes = {TrackingApplication.class})
@AutoConfigureMockMvc
@Transactional
class BasicResourceTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void basic() {
        Assertions.assertThat(mockMvc).isNotNull();
    }
}