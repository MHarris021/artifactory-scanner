package org.mharris.artifactory.artifactoryscanner.services;

import org.junit.jupiter.api.Test;
import org.mharris.artifactory.artifactoryscanner.models.*;
import org.mharris.artifactory.artifactoryscanner.services.clients.ArtifactoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ArtifactoryServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactoryServiceTest.class);

    @MockBean
    private ArtifactoryClient artifactoryClient;

    @Autowired
    private ArtifactoryService artifactoryService;

    @Test
    void testExistence() {
        assertThat(artifactoryClient).isNotNull();
        assertThat(artifactoryService).isNotNull();
        LOGGER.info("ArtifactoryService successfully created.");
    }

    @Test
    void getArtifactsInRepo() {
        Artifact artifact0 = getArtifact0();
        Artifact artifact1 = getArtifact1();
        Artifact artifact2 = getArtifact2();
        Artifact artifact3 = getArtifact3();
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(artifact0);
        artifacts.add(artifact1);
        artifacts.add(artifact2);
        artifacts.add(artifact3);
        FindArtifactRequest artifactRequest = new FindArtifactRequest("jcenter-cache");
        when(artifactoryClient.findArtifacts(artifactRequest.generateAQL())).thenReturn(new ArtifactWrapper(artifacts));
        List<Artifact> artifacts1 = artifactoryService.getArtifactsInRepo("jcenter-cache");
        assertThat(artifacts1).isNotEmpty();
        assertThat(artifacts1).isEqualTo(artifacts);
    }

    @Test
    void getArtifactStatistics() {
        Artifact artifact = getArtifact1();
        ArtifactStatistics artifactStatistics = getArtifactStatistics(artifact, 10);
        when(artifactoryClient.getArtifactStatistics(artifact.getRepo(), artifact.getPath(), artifact.getName())).thenReturn(artifactStatistics);
        ArtifactStatistics artifactStatistics1 = artifactoryService.getArtifactStatistics(artifact);
        assertThat(artifactStatistics1).isNotNull();
        assertThat(artifactStatistics1).isEqualTo(artifactStatistics);
    }

    @Test
    void getPopularArtifacts() throws RepositoryNotFoundException {

        RepoRequest repoRequest = new RepoRequest();
        repoRequest.setRepoKey("jencter-cache");
        FindArtifactRequest artifactRequest = new FindArtifactRequest(repoRequest.getRepoKey());
        repoRequest.setLimit(2);
        Artifact artifact0 = getArtifact0();
        ArtifactStatistics artifactStatistics0 = getArtifactStatistics(artifact0, 1);
        Artifact artifact1 = getArtifact1();
        ArtifactStatistics artifactStatistics1 = getArtifactStatistics(artifact1, 10);
        Artifact artifact2 = getArtifact2();
        ArtifactStatistics artifactStatistics2 = getArtifactStatistics(artifact2, 5);
        Artifact artifact3 = getArtifact3();
        ArtifactStatistics artifactStatistics3 = getArtifactStatistics(artifact3, 100);
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(artifact0);
        artifacts.add(artifact1);
        artifacts.add(artifact2);
        artifacts.add(artifact3);
        when(artifactoryClient.findArtifacts(artifactRequest.generateAQL())).thenReturn(new ArtifactWrapper(artifacts));
        when(artifactoryClient.getArtifactStatistics(anyString(), anyString(), anyString())).
                thenReturn(artifactStatistics0, artifactStatistics1, artifactStatistics2, artifactStatistics3);

        List<ArtifactComposite> composites = artifactoryService.getPopularArtifacts(repoRequest);
        assertThat(composites).isNotEmpty();
        assertThat(composites).hasSize(2);
        assertThat(composites.get(0).getPath()).isEqualTo(artifact3.getPath());
        assertThat(composites.get(0).getDownloadCount()).isEqualTo(artifactStatistics3.getDownloadCount());
        assertThat(composites.get(1).getPath()).isEqualTo(artifact1.getPath());
        assertThat(composites.get(1).getDownloadCount()).isEqualTo(artifactStatistics1.getDownloadCount());


    }

    private Artifact getArtifact0() {
        Artifact artifact = new Artifact();
        artifact.setName("tiles-api-0.0.6.jar");
        artifact.setRepo("jcenter-cache");
        artifact.setPath("org/apache/tiles/tiles-api/0.0.6");
        return artifact;
    }

    private Artifact getArtifact1() {
        Artifact artifact = new Artifact();
        artifact.setName("tiles-api-2.0.6.jar");
        artifact.setRepo("jcenter-cache");
        artifact.setPath("org/apache/tiles/tiles-api/2.0.6");
        return artifact;
    }

    private Artifact getArtifact2() {
        Artifact artifact = new Artifact();
        artifact.setName("tiles-api-2.0.5.jar");
        artifact.setRepo("jcenter-cache");
        artifact.setPath("org/apache/tiles/tiles-api/2.0.5");
        return artifact;
    }

    private Artifact getArtifact3() {
        Artifact artifact = new Artifact();
        artifact.setName("tiles-api-2.0.0.jar");
        artifact.setRepo("jcenter-cache");
        artifact.setPath("org/apache/tiles/tiles-api/2.0.0");
        return artifact;
    }

    private ArtifactStatistics getArtifactStatistics(Artifact artifact, int count) {
        ArtifactStatistics artifactStatistics = new ArtifactStatistics();
        artifactStatistics.setUri(URI.create("http://localhost/artifactory/" + artifact.getRepo() + "/" + artifact.getPath()));
        artifactStatistics.setDownloadCount(count);
        return artifactStatistics;
    }
}