package br.ucsal.fitsys.fitsys.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FirebaseService {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/fitsys-ucsal.appspot.com/o/%s?alt=media";

    private String uploadFile(File file, String fileName, String extensao) throws IOException {
        BlobId blobId = BlobId.of("fitsys-ucsal.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/".concat(extensao.substring(1,extensao.length()))).build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase_admin.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public String upload(MultipartFile multipartFile, String tipo) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            String extensao = this.getExtension(fileName);

            extensao = (extensao.equals(".jpeg")) ? ".png" : extensao;

            fileName = tipo.concat(UUID.randomUUID().toString().concat(extensao));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFile(file, fileName, extensao);                            // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return TEMP_URL;                                                              // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }



}
