package io.github.jxch.capitals.cloud.common;

import cn.hutool.core.bean.BeanUtil;
import io.github.jxch.capitals.oauth2.model.JwtAuthUser;
import org.springframework.web.server.WebSession;

public class JwtAuthUserUtil {

    public static JwtAuthUser getUser(final WebSession session) {
        JwtAuthUser jwtAuthUser = BeanUtil.toBean(
                session.getAttributes().get(JwtAuthUser.class.getSimpleName()),
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
