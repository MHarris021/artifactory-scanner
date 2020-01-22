package org.mharris.artifactory.artifactoryscanner;

import org.mharris.artifactory.artifactoryscanner.services.clients.AuthInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class ArtifactoryScannerApplicationConfiguration {

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
