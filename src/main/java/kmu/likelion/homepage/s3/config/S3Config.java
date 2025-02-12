package kmu.likelion.homepage.s3.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3Config {

    @Value("${aws.credentials.access-key}")
    private String access_key;

    @Value("${aws.credentials.secret-key}")
    private String secret_key;

    @Getter
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.credentials.region}")
    private String region;

    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(access_key, secret_key)))
                .build();
    }

}
