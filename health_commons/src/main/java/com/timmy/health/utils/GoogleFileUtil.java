package com.timmy.health.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class GoogleFileUtil {

    private static final String BUCKET_NAME = "health_pictures";
    private static final String GCP_URL = "https://www.googleapis.com/auth/cloud-platform";


    private static GoogleCredentials getGoogleAccess() throws IOException {
        try (InputStream is = GoogleFileUtil.class.getClassLoader().getResourceAsStream("json/key_for_access.json")) {
            if (is == null) {
                throw new FileNotFoundException("找不到該文件");
            }
            return GoogleCredentials.fromStream(is).createScoped(Lists.newArrayList((GCP_URL)));
        }
    }

    // get the storage
    private static Storage getStorage(GoogleCredentials googleCredentials) {
        return StorageOptions.newBuilder().setCredentials(googleCredentials).build().getService();
    }

    @NotNull
    public static String uploadFile(String filePath, String fileName) throws IOException {
        GoogleCredentials credentials = getGoogleAccess();
        Storage storage = getStorage(credentials);
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, bytes);

        blob.toBuilder().setContentType("image/jpg").build().update();
        return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + fileName;
    }

    @NotNull
    public static String uploadFile(byte[] filePath, String fileName) throws IOException {
        GoogleCredentials credentials = getGoogleAccess();
        Storage storage = getStorage(credentials);

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, filePath);

        blob.toBuilder().setContentType("image/jpg").build().update();
        return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + fileName;
    }

    public static void deleteFile(String fileName) throws IOException {
        GoogleCredentials credentials = getGoogleAccess();
        Storage storage = getStorage(credentials);

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo);
        Blob.BlobSourceOption option = Blob.BlobSourceOption.decryptionKey(fileName);
        blob.delete(option);
    }

}
