package org.mharris.artifactory.artifactoryscanner.models;

import lombok.Data;

import java.net.URI;
import java.util.Date;

@Data
public class ArtifactComposite {
    private String name;
    private String repo;
    private String path;
    private Date created;
    private Date modified;
    private Date updated;
    private URI uri;
    private int downloadCount;
    private long lastDownloaded;

}
