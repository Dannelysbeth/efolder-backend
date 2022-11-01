package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.ProfilePictureNotFoundException;
import com.example.efolder.model.ProfilePicture;
import com.example.efolder.model.User;
import com.example.efolder.repository.ProfilePictureRepository;
import com.example.efolder.service.definition.ProfilePictureService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProfilePictureServiceImpl implements ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;
    private final UserService userService;

    @Override
    public ProfilePicture saveProfilePicture(ProfilePicture profilePicture) {
        User user = userService.getUser(profilePicture.getUser().getUsername());
        user.setProfilePicture(profilePicture);
        userService.updateUser(user);
        System.out.println(user.getProfilePicture().getName());
        return profilePictureRepository.save(profilePicture);
    }

    @Override
    public ProfilePicture getProfilePicture(Long id) {
        return profilePictureRepository.findById(id)
                .orElseThrow(ProfilePictureNotFoundException::new);
    }

    @Override
    public ProfilePicture getProfilePictureByUsername(String username) {
        return profilePictureRepository.findByUser_Username(username)
                .orElseThrow(ProfilePictureNotFoundException::new);
    }
}
