package org.mharris.artifactory.artifactoryscanner.controllers;

import org.mharris.artifactory.artifactoryscanner.services.ArtifactoryClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController {
    private ArtifactoryClientService artifactoryClientService;

    public HomeControllerImpl(ArtifactoryClientService artifactoryClientService) {
        this.artifactoryClientService = artifactoryClientService;
    }
}
