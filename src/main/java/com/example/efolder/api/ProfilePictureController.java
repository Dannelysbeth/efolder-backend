package com.example.efolder.api;

import com.example.efolder.model.ProfilePicture;
import com.example.efolder.model.UserInfo;
import com.example.efolder.model.dto.requests.AddProfilePictureRequest;
import com.example.efolder.model.dto.respones.ProfilePictureResponse;
import com.example.efolder.service.definition.ProfilePictureService;
import com.example.efolder.service.definition.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profilePicture")
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;
    private final UserInfoService userInfoService;

    @PreAuthorize("permitAll()")
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadProfilePicture(@PathVariable Long id){
        ProfilePicture profilePicture = profilePictureService.getProfilePicture(id);
        ByteArrayResource resource = new ByteArrayResource(profilePicture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profilePicture.getName() + "\"")
                .body(resource);
    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/upload")
    public ResponseEntity<ProfilePictureResponse> uploadMyProfilePicture(@RequestParam("file") MultipartFile file) {
        UserInfo loggedUser = userInfoService.getLoggedUser();
        ProfilePicture profilePicture = new AddProfilePictureRequest().profilePictureRequest(file, loggedUser);
        return ResponseEntity.accepted().body(ProfilePictureResponse.builder()
                .profilePicture(profilePictureService.saveProfilePicture(profilePicture))
                .build());
    }

}
