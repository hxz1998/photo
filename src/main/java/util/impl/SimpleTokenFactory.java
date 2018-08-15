package util.impl;

import model.Token;
import util.PropertiesFactory;
import util.TokenFactory;

public class SimpleTokenFactory implements TokenFactory {

    private Token token = null;
    private PropertiesFactory propertiesFactory;

    @Override
    public Token getToken() {
        propertiesFactory = new SimplePropertiesFactory();
        token = new Token();
        token.setACCESS_KEY(propertiesFactory.getPropertiesValue("ACCESS_KEY"));
        token.setBUCKET(propertiesFactory.getPropertiesValue("BUCKET"));
        token.setSECRET_KEY(propertiesFactory.getPropertiesValue("SECRET_KEY"));
        return token;
    }

}
