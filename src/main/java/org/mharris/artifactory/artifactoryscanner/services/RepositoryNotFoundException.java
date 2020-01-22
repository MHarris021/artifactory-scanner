package org.mharris.artifactory.artifactoryscanner.services;

public class RepositoryNotFoundException extends Throwable {
    public RepositoryNotFoundException() {
        super();
    }

    public RepositoryNotFoundException(String message) {
        super(message);
    }

    public RepositoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public RepositoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
