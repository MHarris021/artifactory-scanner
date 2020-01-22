package org.mharris.artifactory.artifactoryscanner.controllers;

import org.mharris.artifactory.artifactoryscanner.models.ArtifactComposite;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.mharris.artifactory.artifactoryscanner.services.ArtifactoryService;
import org.mharris.artifactory.artifactoryscanner.services.RepositoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeControllerImpl.class);

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
    @GetMapping(path = "/results")
    public ModelAndView results(@ModelAttribute("artifacts") ArrayList<ArtifactComposite> artifactComposites) {
        ModelAndView modelAndView = new ModelAndView("results");
        LOGGER.debug("Artifacts Returned: " + artifactComposites);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("artifacts", artifactComposites);
        LOGGER.debug("Model Map: " + modelMap);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @Override
    @PostMapping(path = "/repoRequest")
    public String requestPopularRepositories(@ModelAttribute("RepoRequest") RepoRequest repoRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String redirect = "";
        LOGGER.debug("RepoRequest: " + repoRequest);
        if (repoRequest.getLimit() < 2) {
            repoRequest.setLimit(2);
        }
        List<ArtifactComposite> artifacts;
        try {
            artifacts = artifactoryService.getPopularArtifacts(repoRequest);
            LOGGER.debug("Artifact Composites Returned: " + artifacts);
            redirectAttributes.addFlashAttribute("artifacts", artifacts);//.addFlashAttribute("artifacts", artifacts);
            redirect = "redirect:/results";
        } catch (RepositoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("javax.servlet.error.exception", e);
            redirectAttributes.addFlashAttribute("javax.servlet.error.status_code", HttpStatus.I_AM_A_TEAPOT);
            return "redirect:/error";
        }
        return redirect;
    }
}
