package services;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class FileServiceTest {

    private FileService fileService;

    @Before
    public void init() {
        fileService = new FileService();
    }

    public void uploadImage() {
        fileService.uploadImage(new File("src/main/resources/img1.jpg"));
        fileService.uploadImage(new File("src/main/resources/img2.jpg"));
        fileService.uploadImage(new File("src/main/resources/img3.jpg"));
        fileService.uploadImage(new File("src/main/resources/img4.jpg"));
        fileService.uploadImage(new File("src/main/resources/img5.jpg"));
    }

    public void getFileInfo() {
        fileService.getFileInfo("FipTGppRRzv4aMhHTOiqqDETXpc6");
    }

    public void deleteFile() {
        fileService.deleteFile("FipTGppRRzv4aMhHTOiqqDETXpc6");
    }

    public void getFileList() {
        fileService.getFileList();
    }

    public void batchDelete() {
        fileService.batchDelete();
    }
}