package org.mharris.artifactory.artifactoryscanner.services.clients;


import org.mharris.artifactory.artifactoryscanner.models.ArtifactStatistics;
import org.mharris.artifactory.artifactoryscanner.models.ArtifactWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "artifactory-client", url = "http://35.225.195.38/artifactory")
public interface FeignArtifactoryClient extends ArtifactoryClient {

    @Override
    @GetMapping(path = "/api/storage/{repoKey}/{artifactPath}/{artifactName}?stats")
    ArtifactStatistics getArtifactStatistics(@PathVariable String repoKey, @PathVariable String artifactPath, @PathVariable String artifactName);

    @Override
    @PostMapping(path = "/api/search/aql")
    ArtifactWrapper findArtifacts(String aql);
}
