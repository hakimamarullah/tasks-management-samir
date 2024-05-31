package com.micro.fintech.utils;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/31/2024 2:18 PM
@Last Modified 5/31/2024 2:18 PM
Version 1.0
*/

import com.micro.fintech.controller.AuthController;

import java.security.Principal;
import java.util.Optional;

public class AuthUtils {

    private AuthUtils(){}

    /**
     * Return -1 if the principal is null or the string principal.getName() does not match
     * this pattern "userId,username".
     * @param principal {@link Principal}
     * @return Long
     */
    public static Long getCurrentUserId(Principal principal) {
        String userDetails = Optional.ofNullable(principal).map(Principal::getName).orElse("-1,");
        return Long.parseLong(userDetails.split(",")[0]);
    }
}
