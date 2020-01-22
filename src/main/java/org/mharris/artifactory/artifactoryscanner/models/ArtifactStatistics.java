package org.mharris.artifactory.artifactoryscanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.net.URI;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtifactStatistics {
    private URI uri;
    private int downloadCount;
    private long lastDownloaded;
    private String lastDownloadedBy;
    private int remoteDownloadCount;
    private int remoteLastDownloaded;
}
