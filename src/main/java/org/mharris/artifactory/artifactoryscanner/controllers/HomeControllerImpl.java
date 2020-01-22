package org.mharris.artifactory.artifactoryscanner.controllers;

import org.mharris.artifactory.artifactoryscanner.models.ArtifactComposite;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.mharris.artifactory.artifactoryscanner.services.ArtifactoryService;
import org.mharris.artifactory.artifactoryscanner.services.RepositoryNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {
    private ArtifactoryService artifactoryService;

    public HomeControllerImpl(ArtifactoryService artifactoryService) {
        this.artifactoryService = artifactoryService;
    }

    @Override
    @GetMapping(path = "/*")
    public ModelAndView index() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(new RepoRequest());
        return new ModelAndView("index", modelMap);
    }

    @Override
    @GetMapping(path = "/errors")
    public ModelAndView errors(ModelMap modelMap) {
        return new ModelAndView("errors", modelMap);
    }

    @Override
    @GetMapping(path = "/results")
    public ModelAndView results(ModelMap modelMap) {

        return new ModelAndView("results", modelMap);
    }

    @Override
    @PostMapping(path = "/repoRequest")
    public ModelAndView requestPopularRepositories(@ModelAttribute("RepoRequest") RepoRequest repoRequest, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/results");
        List<ArtifactComposite> artifacts;
        try {
            artifacts = artifactoryService.getPopularArtifacts(repoRequest);
            modelAndView.addObject("artifacts", artifacts);
        } catch (RepositoryNotFoundException e) {
            modelAndView.setViewName("redirect:/errors");
            modelAndView.addObject("repoRequest", repoRequest);
        }
        return modelAndView;
    }
}
