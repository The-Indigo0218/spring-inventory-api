package indigodev.com.co.springinventoryapi.service;

import indigodev.com.co.springinventoryapi.dto.auth.AuthenticationRequest;
import indigodev.com.co.springinventoryapi.dto.auth.AuthenticationResponse;
import indigodev.com.co.springinventoryapi.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
}
