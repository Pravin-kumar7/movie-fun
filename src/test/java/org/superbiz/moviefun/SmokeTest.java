package org.superbiz.moviefun;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmokeTest {

    @Test
    public void smokeTest() {
        RestTemplate restTemplate = new RestTemplate();

        String homePage = restTemplate.getForObject(url("/"), String.class);

        assertThat(homePage, containsString("Please select one of the following links:"));

        String setupPage = restTemplate.getForObject(url("/setup"), String.class);

        assertThat(setupPage, containsString("Bahu"));
        assertThat(setupPage, containsString("Mag"));
        assertThat(setupPage, containsString("Bad"));
        assertThat(setupPage, containsString("Luc"));
        assertThat(setupPage, containsString("troy"));

        String movieFunPage = restTemplate.getForObject(url("/moviefun"), String.class);

        assertThat(movieFunPage, containsString("troy"));
        assertThat(movieFunPage, containsString("Bahu"));
    }

    private String url(String path) {
        String baseUrl = "http://localhost:8080/";
        String envUrl = System.getenv("MOVIE_FUN_URL");

        if (envUrl != null && !envUrl.isEmpty()) {
            baseUrl = envUrl;
        }

        return baseUrl + path;
    }
}
