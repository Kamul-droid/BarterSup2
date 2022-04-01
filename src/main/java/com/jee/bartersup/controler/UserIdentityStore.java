package com.jee.bartersup.controler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@Named
@ApplicationScoped
public class UserIdentityStore implements IdentityStore {

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
       if (credential.compareTo("kevi","pass")){
           return new CredentialValidationResult("kevi",new HashSet<>(Arrays.asList("admin","basic")));
       }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
