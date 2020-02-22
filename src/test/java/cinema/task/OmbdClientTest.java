package cinema.task;

import cinema.task.clients.OmdbClient;
import cinema.task.models.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OmbdClientTest {

    @Autowired
    private OmdbClient omdbClient;

    @Test
    public void testOmdbClient() {
        Assert.assertNotNull(omdbClient.getMoviesByTitle("batman", 1));
    }
}
