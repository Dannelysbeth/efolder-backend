package com.example.efolder.model.dto.requests;

import com.example.efolder.model.ProfilePicture;
import com.example.efolder.model.UserInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddProfilePictureRequest {
    public ProfilePicture profilePictureRequest(MultipartFile file, UserInfo owner){
        return ProfilePicture.builder()
                .owner(owner)
                .file(file)
                .build();
    }
}
