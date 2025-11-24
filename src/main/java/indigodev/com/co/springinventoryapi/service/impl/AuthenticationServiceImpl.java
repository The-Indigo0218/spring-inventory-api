package indigodev.com.co.springinventoryapi.service.impl;

import indigodev.com.co.springinventoryapi.domain.User;
import indigodev.com.co.springinventoryapi.domain.enums.UserRole;
import indigodev.com.co.springinventoryapi.dto.auth.AuthenticationRequest;
import indigodev.com.co.springinventoryapi.dto.auth.AuthenticationResponse;
import indigodev.com.co.springinventoryapi.dto.auth.RegisterRequest;
import indigodev.com.co.springinventoryapi.exception.ResourceNotFoundException;
import indigodev.com.co.springinventoryapi.repository.UserRepository;
import indigodev.com.co.springinventoryapi.security.JwtService;
import indigodev.com.co.springinventoryapi.service.AuthenticationService;
import indigodev.com.co.springinventoryapi.util.EnumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;
    private final EnumMapper  enumMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        User user = userRepository.findByUsername(request.username()).orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + request.username()));

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        UserRole role = enumMapper.userRoleMapper(request.role());
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(role)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new  AuthenticationResponse(jwtToken);
    }

    private void validateRoleHierarchy(Authentication currentAuth, String roleToCreateSt ){
        String currentRoleStr = currentAuth.getAuthorities().iterator().next().getAuthority();

        UserRole currentRole = enumMapper.userRoleMapper(currentRoleStr);
        UserRole roleToCreate = enumMapper.userRoleMapper(roleToCreateSt);

        if(currentRole.equals(UserRole.ADMIN) && roleToCreate.equals(UserRole.SUPER_ADMIN) ) {
            throw new AccessDeniedException("Admin cannot create a super_admin role");
        }
        if(currentRole.equals(UserRole.ADMIN) && roleToCreate.equals(UserRole.ADMIN) ) {
            throw new AccessDeniedException("Admin cannot create a admin role");
        }
        if(currentRole.equals(UserRole.INVENTORY_MANAGER) ) {
            throw new AccessDeniedException("Inventory manager cannot create any role");
        }
    }
}
