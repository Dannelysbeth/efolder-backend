package com.example.efolder;

import com.example.efolder.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan({"com.example.efolder"})
@EntityScan("com.example.efolder")
@EnableJpaRepositories("com.example.efolder")
public class DocumentManagerApplicationTests {
    private final static String PATHNAME = "D:\\my_stuff\\dokumenty\\zeznanie-podatkowe-Daniela.pdf";

    @Autowired
    private DocumentRepository documentRepository;

    @Test
//    @Rollback(false)
    void testInsertDocument() throws IOException {
//        File file = new File(PATHNAME);
//        Document document = new Document();
//        Date uploadTime = new Date();
//        uploadTime.setTime(System.currentTimeMillis());
//        document.setName(file.getName());
//        byte[] bytes = Files.readAllBytes(file.toPath());
//        document.setContent(bytes);
//        document.setUploadTime(uploadTime);
//        long fileSize = bytes.length;
//        document.setSize(fileSize);
//
//        Document savedDoc = documentRepository.save(document);
//
//        Document existDoc = documentRepository.findById(savedDoc.getId()).orElseThrow(DocumentNotFoundException::new);
//


    }
}
