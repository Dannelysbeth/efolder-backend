package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.EmailExistsException;
import com.example.efolder.exceptions.RoleNotFoundException;
import com.example.efolder.exceptions.UserNotFoundException;
import com.example.efolder.model.User;
import com.example.efolder.repository.RoleRepository;
import com.example.efolder.repository.UserRepository;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService , UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean emailExistsInDatabase(User user){
        return userRepository.existsByEmail(user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        log.info("User {} found in database", username);
        Collection<SimpleGrantedAuthority> authorities =  new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User saveUser(User user) {
//        log.info("Password of updated user {} : {} ", user.getUsername(), user.getPassword());
//        User foundUser = userRepository.getById(user.getId());
//        log.info("Password of old user {} : {} ", foundUser.getUsername(), foundUser.getPassword());
//        if(foundUser!=null && foundUser.getPassword().equals(user.getPassword())){
//            log.info("Password of user {} match. No need for update", user.getUsername());
//                return userRepository.save(user);
//        }
//        log.info("Password of user {} must be updated", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User createRegularEmployee(User user) {
        if(emailExistsInDatabase(user))
            throw new EmailExistsException();
        user.addRole(roleRepository.findByRoleName("ROLE_REGULAR_EMPLOYEE")
                .orElseThrow(RoleNotFoundException::new));
        return saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
       User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
       user.getRoles()
               .add(roleRepository.findByRoleName(roleName)
               .orElseThrow(RoleNotFoundException::new));
       return userRepository.save(user);
    }

    @Override
    public boolean usernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
//        user.getRoles().remove(user);
//        userRepository.save(user);
       userRepository.delete(user);
    }

    @Override
    public User getLoggedUser() {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            return getUser(username);
        }
        username = principal.toString();
        return getUser(username);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createSuperAdmin(User user) {
        if(emailExistsInDatabase(user))
            throw new EmailExistsException();
        user.addRole(roleRepository.findByRoleName("ROLE_SUPER_ADMIN")
                .orElseThrow(RoleNotFoundException::new));
        return saveUser(user);
    }
//
//    @Override
//    public User addSuperAdminRole(User user) {
//        return null;
//    }
//
//    @Override
//    public User addRegularEmployeeRole(User user) {
//        return null;
//    }
//
//    @Override
//    public User addManagerRole(User user) {
//        return null;
//    }
//
//    @Override
//    public User addHRAdminRole(User user) {
//        return null;
//    }

    @Override
    public User changePassword(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return saveUser(user);
    }


}
