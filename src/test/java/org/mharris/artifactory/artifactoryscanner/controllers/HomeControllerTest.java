package org.mharris.artifactory.artifactoryscanner.controllers;

import org.junit.jupiter.api.Test;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.mharris.artifactory.artifactoryscanner.services.ArtifactoryService;
import org.mharris.artifactory.artifactoryscanner.services.RepositoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Import(HomeControllerTest.HomeControllerTestConfiguration.class)
class HomeControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeControllerTest.class);

    @MockBean
    private ArtifactoryService artifactoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Test
    void testExistence() {
        assertThat(mockMvc).isNotNull();
        assertThat(artifactoryService).isNotNull();
        assertThat(homeController).isNotNull();
        LOGGER.info("Home Controller Found.");
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void errors() throws Exception {
        mockMvc.perform(get("/error")).andDo(print()).andExpect(view().name("errors"));
    }

    @Test
    void results() throws Exception {
        mockMvc.perform(get("/results")).andDo(print()).andExpect(view().name("results")).andExpect(status().isOk());
    }

    @Test
    void requestPopularRepositoriesErrors() throws Exception, RepositoryNotFoundException {
        RepoRequest repoRequest = new RepoRequest();
        repoRequest.setRepoKey("jcenter-poop");
        when(artifactoryService.getPopularArtifacts(any())).thenThrow(new RepositoryNotFoundException());
        mockMvc.perform(post("/repoRequest").param("repoRequest", String.valueOf(repoRequest))).andDo(print()).andExpect(redirectedUrl("/error"));
    }

    @Test
    void requestPopularRepositoriesResults() throws Exception, RepositoryNotFoundException {
        RepoRequest repoRequest1 = new RepoRequest();
        repoRequest1.setRepoKey("jcenter-cache");
        when(artifactoryService.getPopularArtifacts(repoRequest1)).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/repoRequest").param("repoRequest", String.valueOf(repoRequest1))).andDo(print()).andExpect(redirectedUrl("/results"));

    }
}