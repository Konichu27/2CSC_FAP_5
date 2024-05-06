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
    private int fileId;
    private String fileName;

    public UploadedFile(int fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    public int getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }
}
