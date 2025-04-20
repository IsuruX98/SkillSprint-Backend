package com.skillsprint.apigateway.Filter;

        import com.skillsprint.apigateway.util.JWTUtill;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.cloud.gateway.filter.GatewayFilter;
        import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.server.reactive.ServerHttpRequest;
        import org.springframework.stereotype.Component;
        import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JWTUtill jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();


            // 🔍 Debug: Always print
            System.out.println("Request Path: " + request.getURI().getPath());
            System.out.println("Is Secured: " + validator.isSecured.test(request));
            if (validator.isSecured.test(request)) {
                HttpHeaders headers = request.getHeaders(); //get headers from request
                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) { //check weather token is available in request header
                    return Mono.error(new RuntimeException("Missing authorization header"));
                }

                String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    try {
                        jwtUtil.validateToken(authHeader); //validate the token

                            String userRole = jwtUtil.extractRole(token); //extract role from token
                              System.out.println(token);
                            String userEmail = jwtUtil.extractUserName(token); //extract mail from token
                        System.out.println(userEmail);

                        //add userRole and userEmail to request header
                        ServerHttpRequest modifiedRequest = request.mutate()
                                    .header("userRole", userRole)
                                    .header("userEmail",userEmail)
                                    .build();

                            return chain.filter(exchange.mutate().request(modifiedRequest).build());

                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Invalid access"));
                    }
                } else {
                    return Mono.error(new RuntimeException("Invalid authorization header format"));
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
