package org.mharris.artifactory.artifactoryscanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artifact {
    private String repo;
    private String path;
    private String name;
    private String type;
    private long size;
    private Date created;
    private String createdBy;
    private Date modified;
    private String modifiedBy;
    private Date updated;

}
