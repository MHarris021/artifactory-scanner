package org.mharris.artifactory.artifactoryscanner.services;

import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.model.LightweightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.jfrog.artifactory.client.model.impl.RepositoryTypeImpl.*;

@Service
public class ArtifactoryClientServiceImpl implements ArtifactoryClientService {

    private Artifactory artifactory;

    public ArtifactoryClientServiceImpl(Artifactory artifactory) {
        this.artifactory = artifactory;
    }

    @Override
    public List<String> getRepos() {
        List<LightweightRepository> localRepositories = artifactory.repositories().list(LOCAL);
        List<LightweightRepository> remoteRepositories = artifactory.repositories().list(REMOTE);
        List<LightweightRepository> virtualRepositories = artifactory.repositories().list(VIRTUAL);

        List<String> list = new ArrayList<>();
        localRepositories.forEach(rep -> {
            list.add(rep.getKey());
        });
        remoteRepositories.forEach(rep -> {
            list.add(rep.getKey());
        });
        virtualRepositories.forEach(rep -> {
            list.add(rep.getKey());
        });
        return list;
    }
}
