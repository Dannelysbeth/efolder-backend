package com.example.efolder.api;

import com.example.efolder.model.Document;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.AddDocumentRequest;
import com.example.efolder.model.dto.responses.DocumentResponse;
import com.example.efolder.model.enums.FileCategory;
import com.example.efolder.service.definition.DocumentService;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the document flow API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;
    private final UserService userService;

    /**
     * Views document in browser
     *
     * @param id - id of provided document
     * @return resource in form of a viewable pdf document
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewDocument(@PathVariable Long id) {
        Document document = documentService.getDocument(id);
        ByteArrayResource resource = new ByteArrayResource(document.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + document.getName() + "\"")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }

    /**
     * Downloads the document from database
     *
     * @param id - the id of the document
     * @return start downloading file
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id) {
        Document document = documentService.getDocument(id);
        ByteArrayResource resource = new ByteArrayResource(document.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(resource);
    }

    /**
     * Gets the document information
     *
     * @param id - the id of the document
     * @return the document information
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("/info/id={id}")
    public ResponseEntity<Document> getDocumentInfo(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    /**
     * Gets all documents information from selected user
     *
     * @param username - username of user, from whom documents should be selected
     * @return users documents' information
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("info/all/{username}")
    public ResponseEntity<List<DocumentResponse>> getAllDocumentsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(documentService.getAllDocumentsByUsername(username).stream().map(
                document -> DocumentResponse.builder()
                        .document(document)
                        .build()
        ).collect(Collectors.toList()));
    }

    /**
     * Gets all documents information from logged user
     *
     * @param username - username of user, from whom documents should be selected
     * @return users documents' information
     */
    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("info/all")
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok(documentService.getAllDocumentsByUsername(loggedUser.getUsername()).stream().map(
                document -> DocumentResponse.builder()
                        .document(document)
                        .build()
        ).collect(Collectors.toList()));
    }

    /**
     * Gets all documents information from logged user
     *
     * @param type - the category of files that should be selected
     * @return users documents' information
     */
    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("info/{type}")
    public ResponseEntity<List<DocumentResponse>> getAllDocumentsByCategory(@PathVariable String type) {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok(documentService.getAllDocumentsByUsernameAndFileCategory(loggedUser.getUsername(),
                        FileCategory.transformStringToFileCategory(type))
                .stream().map(
                        document -> DocumentResponse.builder()
                                .document(document)
                                .build()
                ).collect(Collectors.toList()));
    }

    /**
     * Gets all documents information from selected user
     *
     * @param type     - the category of files that should be selected
     * @param username - username of user, from whom documents should be selected
     * @return users documents' information
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_REGULAR_EMPLOYEE')"))
    @GetMapping("info/{type}/{username}")
    public ResponseEntity<List<DocumentResponse>> getAllDocumentsByCategoryAndUser(@PathVariable String type, @PathVariable String username) {
        User user = userService.getUser(username);
        return ResponseEntity.ok(documentService.getAllDocumentsByUsernameAndFileCategory(user.getUsername(),
                        FileCategory.transformStringToFileCategory(type))
                .stream().map(
                        document -> DocumentResponse.builder()
                                .document(document)
                                .build()
                ).collect(Collectors.toList()));
    }


    /**
     * Uploads file to server from logged user
     *
     * @param type - the file category
     * @param file the uploaded file
     * @return information about uploaded file
     */
    @PreAuthorize(("hasAnyRole('ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/upload/{type}")
    public ResponseEntity<DocumentResponse> uploadMyFile(@PathVariable String type,
                                                         @RequestPart("file") MultipartFile file) {
        User loggedUser = userService.getLoggedUser();
        Document document = new AddDocumentRequest().documentRequest(file, loggedUser, type);
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());
    }

    /**
     * Uploads given user's file to server
     *
     * @param file     - the file, that should be uploaded
     * @param username - the name of the given username
     * @param type     the category of the file
     * @return information about the uploaded file
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN','ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/upload/{type}/{username}")
    public ResponseEntity<DocumentResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                       @PathVariable String username,
                                                       @PathVariable String type) {
        User user = userService.getUser(username);
        Document document = new AddDocumentRequest().documentRequest(file, user, type);
        return ResponseEntity.accepted().body(DocumentResponse.builder()
                .document(documentService.saveDocument(document))
                .build());
    }

    /**
     * Deletes file by id
     *
     * @param id - the id of the file, that should be deleted
     */
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_REGULAR_EMPLOYEE')"))
    @PostMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.delete(id);

    }

}
