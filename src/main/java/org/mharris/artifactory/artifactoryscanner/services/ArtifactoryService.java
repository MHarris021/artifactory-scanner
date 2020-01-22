package org.mharris.artifactory.artifactoryscanner.services;

import org.mharris.artifactory.artifactoryscanner.models.Artifact;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactComposite;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactStatistics;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;

import java.util.List;

public interface ArtifactoryService {

    List<Artifact> getArtifactsInRepo(String repoKey) throws RepositoryNotFoundException;

    ArtifactStatistics getArtifactStatistics(Artifact artifact);

    List<ArtifactComposite> getPopularArtifacts(RepoRequest repoRequest) throws RepositoryNotFoundException;
}
