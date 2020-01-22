package org.mharris.artifactory.artifactoryscanner.controllers;

import org.mharris.artifactory.artifactoryscanner.models.RepoRequest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public interface HomeController {

    ModelAndView index();

    ModelAndView errors(ModelMap modelMap);

    ModelAndView results(ModelMap modelMap);

    ModelAndView requestPopularRepositories(@ModelAttribute("RepoRequest") RepoRequest repoRequest, BindingResult bindingResult);
}
