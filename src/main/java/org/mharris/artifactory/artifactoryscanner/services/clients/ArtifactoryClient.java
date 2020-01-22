package org.mharris.artifactory.artifactoryscanner.services.clients;

import org.mharris.artifactory.artifactoryscanner.models.ArtifactStatistics;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactWrapper;

public interface ArtifactoryClient {

    ArtifactStatistics getArtifactStatistics(String repoKey, String artifactPath, String artifactName);

    ArtifactWrapper findArtifacts(String aql);
}
