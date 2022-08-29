package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class RoleRequest {
    @NonNull
    private String roleName;

    public Role roleRequest(){
        return Role.builder()
                .id(null)
                .roleName(roleName)
                .build();
    }

}
