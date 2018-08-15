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

    @Test
    public void uploadImage() {
        fileService.uploadImage(new File("src/main/resources/img1.jpg"));
        fileService.uploadImage(new File("src/main/resources/img2.jpg"));
        fileService.uploadImage(new File("src/main/resources/img3.jpg"));
        fileService.uploadImage(new File("src/main/resources/img4.jpg"));
        fileService.uploadImage(new File("src/main/resources/img5.jpg"));
    }

    @Test
    public void getFileInfo() {
        fileService.getFileInfo("FipTGppRRzv4aMhHTOiqqDETXpc6");
    }

    @Test
    public void deleteFile() {
        fileService.deleteFile("FipTGppRRzv4aMhHTOiqqDETXpc6");
    }

    @Test
    public void getFileList() {
        fileService.getFileList();
    }

    @Test
    public void batchDelete() {
        fileService.batchDelete();
    }
}