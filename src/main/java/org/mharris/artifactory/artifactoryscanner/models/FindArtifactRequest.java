package org.mharris.artifactory.artifactoryscanner.models;

import lombok.Data;

@Data
public class FindArtifactRequest {
    private String repoKey;

    public FindArtifactRequest(String repoKey) {
        this.repoKey = repoKey;
    }

    public String generateAQL() {
        return "items.find({\"repo\":{\"$eq\":" +
                "\"" + repoKey + "\"}})";
    }
}
