package top.peaktop.Model;

import top.peaktop.Utils.ConfigLoader;

import java.io.IOException;

public class OssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String picLoacation;

    public OssConfig() {
        super();
        try {
            this.endpoint = ConfigLoader.loadConfigResource("endpoint");
            this.accessKeyId = ConfigLoader.loadConfigResource("accessKeyId");
            this.accessKeySecret = ConfigLoader.loadConfigResource("accessKeySecret");
            this.bucketName = ConfigLoader.loadConfigResource("bucketName");
            this.picLoacation = ConfigLoader.loadConfigResource("picLocation");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPicLoacation() {
        return picLoacation;
    }

    public void setPicLoacation(String picLoacation) {
        this.picLoacation = picLoacation;
    }


}
