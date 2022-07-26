package com.timmy.health.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class GoogleFileUtil {

    private static final String BUCKET_NAME = "health_pictures";
    private static final String KEY_FOR_ACCESS = "C:\\Users\\examy\\Downloads\\tonal-nucleus-357206-299f76243d44.json";
    private static final String GCP_URL = "https://www.googleapis.com/auth/cloud-platform";


    // get the credential
    private static GoogleCredentials getGoogleAccess() throws IOException {
        return GoogleCredentials
                .fromStream(new FileInputStream(KEY_FOR_ACCESS))
                .createScoped(Lists.newArrayList((GCP_URL)));
    }

    // get the storage
    private static Storage getStorage(GoogleCredentials googleCredentials) {
        return StorageOptions.newBuilder().setCredentials(googleCredentials).build().getService();
    }

    @NotNull
    public static String uploadFile(String filePath, String fileName) throws IOException {

        GoogleCredentials credentials = getGoogleAccess();
        //create the storage object to operate on google cloud storage
        Storage storage = getStorage(credentials);
        //transfer the file to byte to send
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // upload the file to tbe blob
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, bytes);

        blob.toBuilder().setContentType("image/jpg").build().update();
        return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + fileName;

    }

    @NotNull
    public static String uploadFile(byte[] filePath, String fileName) throws IOException {
        // the upload file absolute path
        //read the localStorage with the service json code to get the server authentication
        GoogleCredentials credentials = getGoogleAccess();

        //create the storage object to operate on google cloud storage
        Storage storage = getStorage(credentials);
        //transfer the file to byte to send
        // upload the file to tbe blob
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, filePath);

        //change the fileType to png image/png
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
