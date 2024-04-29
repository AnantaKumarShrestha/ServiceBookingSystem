package com.morgan.project1.servicebookingsystem.encryption;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

@Component
public class EncryptorDecryptor implements AttributeConverter<String,String> {

    @Value("${encryption.algorithm}")
    private String ALGORITHM;

    @Value("${encryption.secret_key}")
    private String SECRET;

    private Key key;
    private Cipher cipher;

    @PostConstruct
    public void init() throws Exception {
        key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
        cipher = Cipher.getInstance(ALGORITHM);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }

}
