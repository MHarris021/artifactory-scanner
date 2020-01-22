package org.mharris.artifactory.artifactoryscanner.models;

import lombok.Data;

@Data
public class RepoRequest {
    private String repoKey;
    private int limit;
}
