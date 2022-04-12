package sn.ept.git.seminaire.cicd.mapper;

import sn.ept.git.seminaire.cicd.TrackingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrackingApplication.class})
class MapperBaseTest { }