package org.mharris.artifactory.artifactoryscanner.controllers;

import org.mharris.artifactory.artifactoryscanner.models.ArtifactComposite;
import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

public interface HomeController {

    ModelAndView index();


    ModelAndView results(@ModelAttribute("artifacts") ArrayList<ArtifactComposite> artifactComposites);

    String requestPopularRepositories(@ModelAttribute("RepoRequest") RepoRequest repoRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes);
}
