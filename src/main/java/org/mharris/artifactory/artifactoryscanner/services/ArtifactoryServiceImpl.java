package org.mharris.artifactory.artifactoryscanner.services;

import org.mharris.artifactory.artifactoryscanner.models.*;
import org.mharris.artifactory.artifactoryscanner.services.clients.ArtifactoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtifactoryServiceImpl implements ArtifactoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactoryServiceImpl.class);

    private ArtifactoryClient artifactoryClient;

    public ArtifactoryServiceImpl(ArtifactoryClient artifactoryClient) {
        this.artifactoryClient = artifactoryClient;
    }


    @Override
    public List<Artifact> getArtifactsInRepo(String repoKey) throws RepositoryNotFoundException {
        FindArtifactRequest artifactRequest = new FindArtifactRequest(repoKey);
        List<Artifact> artifacts = artifactoryClient.findArtifacts(artifactRequest.generateAQL()).getArtifacts();
        if (artifacts == null || artifacts.isEmpty()) {
            throw new RepositoryNotFoundException("Repository: " + repoKey + " is empty or does not exist!");
        }
        artifacts = artifacts.stream().filter(artifact -> {
            String artifactName = artifact.getName();
            return artifactName.substring(artifactName.length() - 4).equals(".jar");
        }).collect(Collectors.toList());
        LOGGER.debug("Artifacts returned: " + artifacts);
        return artifacts;
    }

    @Override
    public ArtifactStatistics getArtifactStatistics(Artifact artifact) {
        ArtifactStatistics statistics = artifactoryClient.getArtifactStatistics(artifact.getRepo(), artifact.getPath(), artifact.getName());
        LOGGER.debug("Artifact Statistics returned: " + statistics);
        return statistics;
    }

    @Override
    public List<ArtifactComposite> getPopularArtifacts(RepoRequest repoRequest) throws RepositoryNotFoundException {
        List<ArtifactComposite> composites = new ArrayList<>();
        List<Artifact> artifacts = this.getArtifactsInRepo(repoRequest.getRepoKey());
        for (Artifact artifact : artifacts) {
            ArtifactStatistics artifactStatistics = this.getArtifactStatistics(artifact);
            ArtifactComposite composite = ArtifactCompositeBuilder.anArtifactComposite().fromArtifactandStatistics(artifact, artifactStatistics).build();
            composites.add(composite);
        }
        composites = composites.stream().sorted(Collections.reverseOrder(Comparator.comparingInt(ArtifactComposite::getDownloadCount))).limit(repoRequest.getLimit()).collect(Collectors.toList());
        LOGGER.debug("Artifact Composites returned: " + composites);
        return composites;
    }
}
