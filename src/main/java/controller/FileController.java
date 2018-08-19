package controller;

import com.alibaba.fastjson.JSON;
import dto.APITarget;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import services.FileService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/v1/file")
public class FileController {

    private FileService fileService;

    @RequestMapping(value = "/getToken", produces = "application/json;charset=utf-8")
    @CrossOrigin("http://localhost:8081")
    @ResponseBody
    public String getToken() {
        APITarget<String> result = new APITarget<>();
        result.setStatus("200");
        result.setData(fileService.getUploadToken());
        result.setMsg("获取token成功");
        return JSON.toJSONString(result);
    }

    public FileService getFileService() {
        return fileService;
    }

    @Resource
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
