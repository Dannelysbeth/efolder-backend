package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.NotValidPictureException;
import com.example.efolder.model.ProfilePicture;
import com.example.efolder.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

@Data
public class AddProfilePictureRequest {

    private boolean checkIfValidPicture(MultipartFile file) {
        String filepath = file.getOriginalFilename();
        assert filepath != null;
        File f = new File(filepath);
        String mimetype = new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    public ProfilePicture profilePictureRequest(MultipartFile file, User owner) throws IOException {
        if (!checkIfValidPicture(file))
            throw new NotValidPictureException();
        else {
            return ProfilePicture.builder()
                    .user(owner)
                    .id(owner.getId())
                    .content(file.getBytes())
                    .name(file.getName())
                    .size(file.getSize())
                    .build();
        }

    }

}
