package org.mharris.artifactory.artifactoryscanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtifactWrapper {

    public ArtifactWrapper() {
    }

    public ArtifactWrapper(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    @JsonProperty("results")
    private List<Artifact> artifacts;
}
