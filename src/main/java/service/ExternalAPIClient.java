package service;
import dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalAPIClient {

    private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com";

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PostDTO[] fetchPosts() {
        String postsUrl = EXTERNAL_API_URL + "/posts";
        return restTemplate.getForObject(postsUrl, PostDTO[].class);
    }
}
