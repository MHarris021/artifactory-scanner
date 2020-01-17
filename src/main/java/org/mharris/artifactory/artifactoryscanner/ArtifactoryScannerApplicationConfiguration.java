package org.mharris.artifactory.artifactoryscanner;

import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClientBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class ArtifactoryScannerApplicationConfiguration {

    @Bean
    public Artifactory artifactory() {
        return ArtifactoryClientBuilder.create().setUrl("http://35.193.82.133/artifactory").build();
    }
}
