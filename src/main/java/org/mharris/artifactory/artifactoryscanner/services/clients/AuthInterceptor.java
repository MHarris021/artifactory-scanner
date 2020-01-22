package org.mharris.artifactory.artifactoryscanner.services.clients;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("X-JFrog-Art-Api", "AKCp5e3yQyM7NhEatwD7dqTrBGQ7rBsXh8DqPTH28gQRNLkggHgDBHadFwyUDu3NSLn2CisA8");
    }
}
