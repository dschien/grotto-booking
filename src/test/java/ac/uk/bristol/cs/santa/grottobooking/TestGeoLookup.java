package ac.uk.bristol.cs.santa.grottobooking;

import com.google.maps.errors.ApiException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestGeoLookup {
    private static final Logger logger = LoggerFactory.getLogger(TestGeoLookup.class);

    @Autowired
    GeoLookup geoLookup;

    @Test
    void simple_test() throws InterruptedException, ApiException, IOException {
        logger.info(geoLookup.geocode("35 Berkeley Square, BS8 1UB"));

    }
}
