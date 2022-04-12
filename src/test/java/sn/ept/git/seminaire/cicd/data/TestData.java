package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.time.Instant;
import java.util.UUID;

public class TestData {

    public TestData(){}

    public static final class Default {
        private Default(){}
        public static final UUID id = UUID.randomUUID();
        public static final Instant createdDate = Instant.now();
        public static final Instant lastModifiedDate = Instant.now();
        public static final int version = 0;
        public static final boolean enabled = true;
        public static final boolean deleted = false;
        public static final  String name = RandomStringUtils.randomAlphanumeric( (SizeMapping.Name.MIN+SizeMapping.Name.MAX)/2);
        public static final  String address=  RandomStringUtils.randomAlphanumeric( (SizeMapping.Adresse.MIN+SizeMapping.Adresse.MAX)/2);
        public static final  String phone=RandomStringUtils.randomNumeric( (SizeMapping.Phone.MIN+SizeMapping.Phone.MAX)/2);
        public static final String email=RandomStringUtils.randomAlphanumeric( (SizeMapping.Email.MIN+SizeMapping.Email.MAX)/2).concat("@ept.sn");
        public static final float longitude= RandomUtils.nextFloat();
        public static final float latitude=RandomUtils.nextFloat();
    }


    public static final class Update {
        private Update(){}
        public static final UUID id = UUID.randomUUID();
        public static final Instant createdDate = Instant.now();
        public static final Instant lastModifiedDate = Instant.now();
        public static final int version = 2;
        public static final boolean enabled = false;
        public static final boolean deleted = true;
        public static final  String name = RandomStringUtils.randomAlphanumeric( (SizeMapping.Name.MIN+SizeMapping.Name.MAX)/2);
        public static final  String address=  RandomStringUtils.randomAlphanumeric( (SizeMapping.Adresse.MIN+SizeMapping.Adresse.MAX)/2);
        public static final  String phone=RandomStringUtils.randomNumeric( (SizeMapping.Phone.MIN+SizeMapping.Phone.MAX)/2);
        public static final String email=RandomStringUtils.randomNumeric( (SizeMapping.Email.MIN+SizeMapping.Email.MAX)/2).concat("@sn.sn");
        public static final float longitude= RandomUtils.nextFloat();
        public static final float latitude=RandomUtils.nextFloat();
    }

}
