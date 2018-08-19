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
import model.KeyStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import util.TokenFactory;
import util.impl.SimpleTokenFactory;

import java.io.File;

@Service
public class FileService implements InitializingBean {

    private KeyStore keyStore;
    private TokenFactory tokenFactory;
    private Configuration cfg;
    private Auth auth;
    private BucketManager bucketManager;

    private void init() {
        tokenFactory = new SimpleTokenFactory();
        keyStore = tokenFactory.getKeyStore();
        cfg = new Configuration(Zone.zone0());
        auth = Auth.create(keyStore.getAccessKey(), keyStore.getSecretKey());
        bucketManager = new BucketManager(auth, cfg);
    }

    public String getUploadToken() {
        return auth.uploadToken(keyStore.getBucket());
    }

    /**
     * 上传示例
     *
     * @param file 要上传的文件
     */
    public void uploadImage(File file) {
        if (file.getName().endsWith(".jpg")) {
            UploadManager uploadManager = new UploadManager(cfg);
            String upToken = auth.uploadToken(keyStore.getBucket());
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
     *
     * @param fileHash 文件的哈希值
     */
    public void getFileInfo(String fileHash) {
        try {
            FileInfo fileInfo = bucketManager.stat(keyStore.getBucket(), fileHash);
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
     *
     * @param fileHash 要删除文件的哈希值
     */
    public void deleteFile(String fileHash) {
        try {
            bucketManager.delete(keyStore.getBucket(), fileHash);
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
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(keyStore.getBucket(), prefix, limit, delimiter);
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
        batchOperations.addDeleteOp(keyStore.getBucket(), files);
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

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public TokenFactory getTokenFactory() {
        return tokenFactory;
    }

    public void setTokenFactory(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    public Configuration getCfg() {
        return cfg;
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public BucketManager getBucketManager() {
        return bucketManager;
    }

    public void setBucketManager(BucketManager bucketManager) {
        this.bucketManager = bucketManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
