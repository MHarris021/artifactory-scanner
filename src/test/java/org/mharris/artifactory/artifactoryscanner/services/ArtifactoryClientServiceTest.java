package org.mharris.artifactory.artifactoryscanner.services;

import org.jfrog.artifactory.client.Artifactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ArtifactoryClientServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactoryClientServiceTest.class);

    @Autowired
    private Artifactory artifactory;

    @Autowired
    private ArtifactoryClientService artifactoryClientService;

    @Test
    void testExistence() {
        assertThat(artifactory).isNotNull();
        assertThat(artifactoryClientService).isNotNull();
    }

    @Test
    void testReposExist() {
        List<String> list = artifactoryClientService.getRepos();
        assertThat(list).isNotEmpty();
        list.forEach(LOGGER::info);
    }
}