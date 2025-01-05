package io.github.jxch.capitals.cloud.common;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.capitals.oauth2.model.JwtAuthUser;
import org.springframework.web.server.WebSession;

import java.util.Objects;

public class JwtAuthUserUtil {

    public static JwtAuthUser getUser(final WebSession session) {
        JwtAuthUser jwtAuthUser = JSON.parseObject(
                Objects.toString(session.getAttributes().get(JwtAuthUser.class.getSimpleName())),
                JwtAuthUser.class
        );

        if (jwtAuthUser == null) {
            throw new IllegalStateException("User not authenticated");
        }

        return jwtAuthUser;
    }

    public static String getUserid(final WebSession session) {
        return getUser(session).getUserid();
    }

}
