package com.url.transform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new resource not found exception.
     *
     * @param errorMessage
     *            the error message
     */
    
    public ResourceNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
