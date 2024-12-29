package io.github.jxch.capitals.cloud.common.model;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@Accessors(chain = true)
public class UserCookie {
    private String email;
    private Boolean emailVerified;
    private String preferredUsername;
    private String name;
    private String givenName;
    private String familyName;
    private String nonce;

    public static UserCookie parseJson(String json) {
        return JSON.parseObject(json, UserCookie.class);
    }

    public static UserCookie parseObject(Object obj) {
        return parseJson(JSON.toJSONString(obj));
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public String toUrlJson() {
        return URLEncoder.encode(toJson(), StandardCharsets.UTF_8);
    }

    public static UserCookie parseUrlJson(String json) {
        return parseJson(URLDecoder.decode(json, StandardCharsets.UTF_8));
    }

}
