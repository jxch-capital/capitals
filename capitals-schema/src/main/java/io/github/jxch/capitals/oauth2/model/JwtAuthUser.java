package io.github.jxch.capitals.oauth2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "oauth2权限认证对象")
public class JwtAuthUser {
    @Schema(description = "subject, 用于 userid")
    private String sub;
    @Schema(description = "邮箱是否已经认证")
    private Boolean emailVerified;
    @Schema(description = "登录用户名")
    private String preferredUsername;
    @Schema(description = "姓")
    private String givenName;
    @Schema(description = "名")
    private String familyName;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "邮箱")
    private String email;

    @JsonProperty("userid")
    @Schema(description = "userid, 使用 sub 属性")
    public String getUserid() {
        return sub;
    }
}
