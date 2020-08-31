package com.uniteddate.facility_manage.common.service.token;


import com.uniteddate.facility_manage.common.util.token.JWTUtils;

import java.util.Map;

public class TokenService {

    /**
     * 获取userId
     * @return
     */
    public static Long getUserId(String token){
        return (Long) JWTUtils.getPayLoad(token).get("userId");
    }

    /**
     * 创建token
     * @return
     */
    public static String createToken(String uuid, Map<String, Object> payload, Long expired) throws Exception {
        return JWTUtils.createJWT(uuid, payload, expired);
    }
}
