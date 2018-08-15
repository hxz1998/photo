package services;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import model.Token;
import util.TokenFactory;
import util.impl.SimpleTokenFactory;

import java.io.File;

public class FileService {

    private Token token;
    private TokenFactory tokenFactory;
    private Configuration cfg;
    private Auth auth;
    private BucketManager bucketManager;

    public FileService() {
        tokenFactory = new SimpleTokenFactory();
        token = tokenFactory.getToken();
        cfg = new Configuration(Zone.zone0());
        auth = Auth.create(token.getACCESS_KEY(), token.getSECRET_KEY());
        bucketManager = new BucketManager(auth, cfg);
    }

    /**
     * 上传示例
     * @param file 要上传的文件
     */
    public void uploadImage(File file) {
        if (file.getName().endsWith(".jpg")) {
            UploadManager uploadManager = new UploadManager(cfg);
            String upToken = auth.uploadToken(token.getBUCKET());
            try {
                Response response = uploadManager.put(file, null, upToken);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response response = ex.response;
                System.err.println(response.toString());
            }
        } else {
            System.out.println("不符合传输要求");
        }
    }

    /**
     * 获取文件信息
     * @param fileHash 文件的哈希值
     */
    public void getFileInfo(String fileHash) {
        try {
            FileInfo fileInfo = bucketManager.stat(token.getBUCKET(), fileHash);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.key);
            System.out.println(fileInfo.putTime);
            System.out.println(fileInfo.mimeType);
        } catch (QiniuException e) {
            System.out.println(e.response.toString());
        }
    }

    /**
     * 删除文件
     * @param fileHash 要删除文件的哈希值
     */
    public void deleteFile(String fileHash) {
        try {
            bucketManager.delete(token.getBUCKET(), fileHash);
        } catch (QiniuException e) {
            System.out.println(e.code());
            System.out.println(e.response.toString());
        }
    }

    /**
     * 获取空间中的文件列表
     */
    public void getFileList() {
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(token.getBUCKET(), prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
            }
        }
    }

    public void batchDelete() {
        String[] files = {
                "Fp7iJYX5RU-8s17lgBDC69JxJjM_",
                "FipTGppRRzv4aMhHTOiqqDETXpc6"
        };
        BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
        batchOperations.addDeleteOp(token.getBUCKET(), files);
        try {
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatuses = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < files.length; i++) {
                BatchStatus status = batchStatuses[i];
                String key = files[i];
                System.out.print(key + '\t');
                System.out.println(status.data);
            }
        } catch (QiniuException e) {

        }
    }
}
