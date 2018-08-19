package util.impl;

import model.KeyStore;
import util.PropertiesFactory;
import util.TokenFactory;

public class SimpleTokenFactory implements TokenFactory {

    private KeyStore keyStore = null;
    private PropertiesFactory propertiesFactory;

    @Override
    public KeyStore getKeyStore() {
        propertiesFactory = new SimplePropertiesFactory();
        keyStore = new KeyStore();
        keyStore.setAccessKey(propertiesFactory.getPropertiesValue("ACCESS_KEY"));
        keyStore.setBucket(propertiesFactory.getPropertiesValue("BUCKET"));
        keyStore.setSecretKey(propertiesFactory.getPropertiesValue("SECRET_KEY"));
        return keyStore;
    }

}
