package com.skillsprint.apigateway.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    //allow paths to request without authentication
    public static final List<String> openApiEndpoints=List.of(
            "/auth/signup",
            "/auth/login",
            "/auth/validate",
            "/eureka",
            "/course-controller/all-approved/",
            "/course-controller/all-courses/**"
    );


    //check incoming paths is secured or not
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
