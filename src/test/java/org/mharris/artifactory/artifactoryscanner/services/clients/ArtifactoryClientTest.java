package org.mharris.artifactory.artifactoryscanner.services.clients;

import org.junit.jupiter.api.Test;
import org.mharris.artifactory.artifactoryscanner.models.Artifact;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactStatistics;
import org.mharris.artifactory.artifactoryscanner.models.FindArtifactRequest;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArtifactoryClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactoryClientTest.class);

    @Autowired
    private ArtifactoryClient artifactoryClient;

    @Test
    void testExistence() {
        assertThat(artifactoryClient).isNotNull();
    }

    @Test
    void testFindArtifacts() {
        RepoRequest repoRequest = new RepoRequest();
        repoRequest.setRepoKey("jcenter-cache");
        FindArtifactRequest artifactRequest = new FindArtifactRequest(repoRequest.getRepoKey());
        List<Artifact> artifacts = artifactoryClient.findArtifacts(artifactRequest.generateAQL()).getArtifacts();
        assertThat(artifacts).isNotNull();
        LOGGER.info("Returned Artifacts: " + artifacts);
    }

    @Test
    void testGetArtifactStatistics() {
        RepoRequest repoRequest = new RepoRequest();
        repoRequest.setRepoKey("jcenter-cache");
        FindArtifactRequest artifactRequest = new FindArtifactRequest(repoRequest.getRepoKey());
        List<Artifact> artifacts = artifactoryClient.findArtifacts(artifactRequest.generateAQL()).getArtifacts();

        Artifact artifact = artifacts.get(0);
        ArtifactStatistics artifactStatistics = artifactoryClient.getArtifactStatistics(artifact.getRepo(), artifact.getPath(), artifact.getName());
        assertThat(artifactStatistics).isNotNull();
    }
}