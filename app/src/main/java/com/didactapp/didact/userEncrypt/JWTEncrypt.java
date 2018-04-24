package com.didactapp.didact.userEncrypt;

import com.didactapp.didact.entities.User;
import com.google.gson.Gson;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * class to encrypt user details for server/client communication - uses RSA
 **/
public class JWTEncrypt {
    public String encrypt(String publicKeyStr, User user) {
        try {
            Gson gson = new Gson();
            JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder().subject(gson.toJson(user)).build();

            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);

            EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);


            RSAEncrypter encrypter = new RSAEncrypter(getRsaPublicKey(publicKeyStr));

            // Do the actual encryption
            jwt.encrypt(encrypter);

            // Serialise to JWT compact form
            String jwtString = jwt.serialize();

            System.out.println(jwtString);
            return jwtString;

        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return "";
    }

    private RSAPublicKey getRsaPublicKey(String publicKey) {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");


            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(android.util.Base64.decode(publicKey, android.util.Base64.DEFAULT));

            return (RSAPublicKey) kf.generatePublic(keySpecX509);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
