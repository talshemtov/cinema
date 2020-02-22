package cinema.task.clients;

import cinema.task.config.CinemaConfig;
import cinema.task.models.Movie;
import cinema.task.models.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Service
public class OmdbClient {

    private final RestTemplate restTemplate;
    private static final String OMDB_API_PATH = "http://www.omdbapi.com/?apikey=bb182d9e&";
    private static final String TITLE_QUERY_PARAM = "&s=";
    private static final String PAGE_QUERY_PARAM = "&page=";

    @Autowired
    public OmdbClient(@Qualifier(CinemaConfig.REST_CLIENT_NAME) RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getMoviesByTitle(String title, int page) {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(title, headers);
        try {
            URI uri = URI.create(OMDB_API_PATH +
                    TITLE_QUERY_PARAM + URLEncoder.encode(title, "UTF-8") +
                    PAGE_QUERY_PARAM + page);
            ResponseEntity<SearchResult> response = restTemplate.exchange(
                    uri, HttpMethod.GET, entity, SearchResult.class);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException("Failed to retrieve movies");
            }
            return response.getBody().getSearch();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
