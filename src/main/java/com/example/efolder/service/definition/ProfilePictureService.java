package com.example.efolder.service.definition;

import com.example.efolder.model.ProfilePicture;

public interface ProfilePictureService {
    ProfilePicture saveProfilePicture(ProfilePicture profilePicture);

    ProfilePicture getProfilePicture(Long id);

    ProfilePicture getProfilePictureByUsername(String username);
}
