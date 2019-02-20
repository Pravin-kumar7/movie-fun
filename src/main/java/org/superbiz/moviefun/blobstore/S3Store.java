package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class S3Store implements BlobStore {

    AmazonS3Client s3Client = null;
    String photoStorageBucket = null;
    public static final String Image_Content_TYPE = "image/jpeg";

    public S3Store(AmazonS3Client s3Client, String photoStorageBucket) {
        this.s3Client = s3Client;
        this.photoStorageBucket = photoStorageBucket;
    }

    @Override
    public void put(Blob blob) throws IOException {
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.addUserMetadata("Content-Type", Image_Content_TYPE);

        s3Client.putObject(photoStorageBucket,blob.name,blob.inputStream,new ObjectMetadata());
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        S3Object object = s3Client.getObject(photoStorageBucket, name);
        InputStream is = object.getObjectContent();
        Blob blob = new Blob(name,is,object.getObjectMetadata().getContentType());
        return Optional.ofNullable(blob);
    }

    @Override
    public void deleteAll() {

        s3Client.deleteBucket(photoStorageBucket);
    }
}
