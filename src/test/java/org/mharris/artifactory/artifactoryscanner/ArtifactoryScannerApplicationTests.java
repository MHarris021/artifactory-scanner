package org.mharris.artifactory.artifactoryscanner;

import org.junit.jupiter.api.Test;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactComposite;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.mharris.artifactory.artifactoryscanner.services.ArtifactoryService;
import org.mharris.artifactory.artifactoryscanner.services.RepositoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class ArtifactoryScannerApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactoryScannerApplicationTests.class);

	@Autowired
	private ArtifactoryService artifactoryService;


	@Test
	void contextLoads() {
		assertThat(artifactoryService).isNotNull();
	}

	@Test
	void testArtifactoryServiceLive() throws RepositoryNotFoundException {
		RepoRequest repoRequest = new RepoRequest();
		repoRequest.setRepoKey("jcenter-cache");
		repoRequest.setLimit(2);
		List<ArtifactComposite> compositeList = artifactoryService.getPopularArtifacts(repoRequest);
		assertThat(compositeList).isNotEmpty();
		assertThat(compositeList).hasSize(2);
		assertThat(compositeList.get(0).getDownloadCount()).isGreaterThanOrEqualTo(compositeList.get(1).getDownloadCount());
		LOGGER.info("Most Popular Artifact: " + compositeList.get(0));
		LOGGER.info("Second Most Popular Artifact: " + compositeList.get(1));
	}


}
