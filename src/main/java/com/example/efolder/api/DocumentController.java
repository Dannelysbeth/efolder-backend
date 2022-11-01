package com.example.efolder.api;

import com.example.efolder.model.Document;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.AddDocumentARequest;
import com.example.efolder.model.dto.respones.DocumentResponse;
import com.example.efolder.service.definition.DocumentService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;
    private final UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id){
        Document document = documentService.getDocument(id);
        ByteArrayResource resource = new ByteArrayResource(document.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(resource);
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id){
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @GetMapping("/{username}")
    public ResponseEntity<List<DocumentResponse>> getAllDocumentsByUsername(@PathVariable String username){
        return ResponseEntity.ok(documentService.getAllDocumentsByUsername(username).stream().map(
                document -> DocumentResponse.builder()
                        .document(document)
                        .build()
        ).collect(Collectors.toList()));
    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/A/upload")
    public ResponseEntity<DocumentResponse> uploadMyAFile(@RequestParam("file") MultipartFile file) {
        User loggedUser = userService.getLoggedUser();
        Document document = new AddDocumentARequest().documentRequest(file, loggedUser, "A");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/B/upload")
    public ResponseEntity<DocumentResponse> uploadMyBFile(@RequestParam("file") MultipartFile file) {
        User loggedUser = userService.getLoggedUser();
        Document document = new AddDocumentARequest().documentRequest(file, loggedUser, "B");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/C/upload")
    public ResponseEntity<DocumentResponse> uploadMyCFile(@RequestParam("file") MultipartFile file) {
        User loggedUser = userService.getLoggedUser();
        Document document = new AddDocumentARequest().documentRequest(file, loggedUser, "C");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/D/upload")
    public ResponseEntity<DocumentResponse> uploadMyDFile(@RequestParam("file") MultipartFile file) {
        User loggedUser = userService.getLoggedUser();
        Document document = new AddDocumentARequest().documentRequest(file, loggedUser, "D");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{username}/A/upload")
    public ResponseEntity<DocumentResponse> uploadAFile(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        User user = userService.getUser(username);
        Document document = new AddDocumentARequest().documentRequest(file, user, "A");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{username}/B/upload")
    public ResponseEntity<DocumentResponse> uploadBFile(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        User user = userService.getUser(username);
        Document document = new AddDocumentARequest().documentRequest(file, user, "B");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{username}/C/upload")
    public ResponseEntity<DocumentResponse> uploadCFile(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        User user = userService.getUser(username);
        Document document = new AddDocumentARequest().documentRequest(file, user, "C");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{username}/D/upload")
    public ResponseEntity<DocumentResponse> uploadDFile(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        User user = userService.getUser(username);
        Document document = new AddDocumentARequest().documentRequest(file, user, "D");
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());
    }
}
