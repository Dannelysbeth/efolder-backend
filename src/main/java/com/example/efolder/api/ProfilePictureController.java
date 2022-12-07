package com.example.efolder.api;

import com.example.efolder.model.ProfilePicture;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.AddProfilePictureRequest;
import com.example.efolder.model.dto.responses.ProfilePictureResponse;
import com.example.efolder.service.definition.ProfilePictureService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Class, that handles profile picture API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profilePicture")
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    private final UserService userService;

    /**
     * Downloads profile picture of given user
     * @param username - the username of the user
     * @return start download of picture
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/download/{username}")
    public ResponseEntity<Resource> downloadProfilePicture(@PathVariable String username){
        ProfilePicture profilePicture = profilePictureService.getProfilePicture(
                userService.getUser(username)
                .getId());
        ByteArrayResource resource = new ByteArrayResource(profilePicture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePicture.getName() + "\"")
                .body(resource);
    }

    /**
     *
     * @return the profile picture to view by user
     */
    @PreAuthorize("permitAll()")
    @GetMapping(value = "/myPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> downloadLoggedUserProfilePicture(){
        ProfilePicture profilePicture = profilePictureService.getProfilePicture(
                userService.getLoggedUser()
                .getId());
        ByteArrayResource resource = new ByteArrayResource(profilePicture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePicture.getName() + "\"")
                .body(resource);
    }
    /**
     *
     * @return the profile picture to view by user
     */
    @PreAuthorize("permitAll()")
    @GetMapping(value = "/view/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> viewUsersProfilePicture(@PathVariable Long id){
        ProfilePicture profilePicture = profilePictureService.getProfilePicture(id);
        ByteArrayResource resource = new ByteArrayResource(profilePicture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePicture.getName() + "\"")
                .body(resource);
    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/upload")
    public ResponseEntity<ProfilePictureResponse> uploadMyProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        User loggedUser = userService.getLoggedUser();
        ProfilePicture profilePicture = new AddProfilePictureRequest().profilePictureRequest(file, loggedUser);
        return ResponseEntity.accepted().body(ProfilePictureResponse.builder()
                .profilePicture(profilePictureService.saveProfilePicture(profilePicture))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @PostMapping("/upload/{username}")
    public ResponseEntity<ProfilePictureResponse> uploadUsersProfilePicture(@PathVariable String username, @RequestParam("file") MultipartFile file) throws IOException {
        User user = userService.getUser(username);
        ProfilePicture profilePicture = new AddProfilePictureRequest().profilePictureRequest(file, user);
        return ResponseEntity.accepted().body(ProfilePictureResponse.builder()
                .profilePicture(profilePictureService.saveProfilePicture(profilePicture))
                .build());
    }

}
