package org.mharris.artifactory.artifactoryscanner.services.clients;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AuthInterceptor implements RequestInterceptor {

    private String accessToken;

    public AuthInterceptor(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-JFrog-Art-Api", this.accessToken);
    }
}
