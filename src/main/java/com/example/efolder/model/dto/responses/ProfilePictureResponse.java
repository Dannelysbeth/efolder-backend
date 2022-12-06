package com.example.efolder.model.dto.responses;

import com.example.efolder.model.ProfilePicture;
import lombok.Builder;
import lombok.Data;

@Data
public class ProfilePictureResponse {
    private Long fileId;

    private String firstname;

    private String lastname;

    private String fileName;

    private long fileSize;


    @Builder
    public ProfilePictureResponse(ProfilePicture profilePicture){
        this.fileId = profilePicture.getId();
        this.firstname = profilePicture.getUser().getFirstname();
        this.lastname = profilePicture.getUser().getLastname();
        this.fileName = profilePicture.getName();
        this.fileSize = profilePicture.getSize();

    }
}
