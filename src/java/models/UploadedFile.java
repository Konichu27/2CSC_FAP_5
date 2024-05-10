/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author pc
 */
public class UploadedFile {
    private String uploader;
    private String filePath;

    public UploadedFile(String username, String fileName) {
        this.uploader = username;
        this.filePath = fileName;
    }

    public String getUploader() {
        return uploader;
    }

    public String getFilePath() {
        return filePath;
    }
}
