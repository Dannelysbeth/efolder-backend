package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.RoleNotFoundException;
import com.example.efolder.model.Role;
import com.example.efolder.repository.RoleRepository;
import com.example.efolder.service.definition.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
