package org.mharris.artifactory.artifactoryscanner.models;

import java.net.URI;
import java.util.Date;

public final class ArtifactCompositeBuilder {
    private String name;
    private String repo;
    private String path;
    private Date created;
    private Date modified;
    private Date updated;
    private URI uri;
    private int downloadCount;
    private long lastDownloaded;

    private ArtifactCompositeBuilder() {
    }

    public static ArtifactCompositeBuilder anArtifactComposite() {
        return new ArtifactCompositeBuilder();
    }

    public ArtifactCompositeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ArtifactCompositeBuilder withRepo(String repo) {
        this.repo = repo;
        return this;
    }

    public ArtifactCompositeBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public ArtifactCompositeBuilder withCreated(Date created) {
        this.created = created;
        return this;
    }

    public ArtifactCompositeBuilder withModified(Date modified) {
        this.modified = modified;
        return this;
    }

    public ArtifactCompositeBuilder withUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public ArtifactCompositeBuilder withUri(URI uri) {
        this.uri = uri;
        return this;
    }

    public ArtifactCompositeBuilder withDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }

    public ArtifactCompositeBuilder withLastDownloaded(long lastDownloaded) {
        this.lastDownloaded = lastDownloaded;
        return this;
    }

    public ArtifactCompositeBuilder fromArtifact(Artifact artifact) {
        this.withName(artifact.getName()).withRepo(artifact.getRepo()).withPath(artifact.getPath());
        this.withCreated(artifact.getCreated()).withModified(artifact.getModified()).withUpdated(artifact.getUpdated());
        return this;
    }

    public ArtifactCompositeBuilder fromArtifactStatistics(ArtifactStatistics artifactStatistics) {
        this.withUri(artifactStatistics.getUri()).withDownloadCount(artifactStatistics.getDownloadCount()).withLastDownloaded(artifactStatistics.getLastDownloaded());
        return this;
    }

    public ArtifactCompositeBuilder fromArtifactandStatistics(Artifact artifact, ArtifactStatistics artifactStatistics) {
        this.fromArtifact(artifact).fromArtifactStatistics(artifactStatistics);
        return this;
    }


    public ArtifactComposite build() {
        ArtifactComposite artifactComposite = new ArtifactComposite();
        artifactComposite.setName(name);
        artifactComposite.setRepo(repo);
        artifactComposite.setPath(path);
        artifactComposite.setCreated(created);
        artifactComposite.setModified(modified);
        artifactComposite.setUpdated(updated);
        artifactComposite.setUri(uri);
        artifactComposite.setDownloadCount(downloadCount);
        artifactComposite.setLastDownloaded(lastDownloaded);
        return artifactComposite;
    }
}
