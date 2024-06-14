package com.isa.PrivateClinicContracts.model;

import com.isa.PrivateClinicContracts.security.authorization.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.isa.PrivateClinicContracts.security.authorization.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    ))
    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}