package net.hwyz.iov.cloud.tsp.umr.api.contract.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * 推送请求
 *
 * @author hwyz_leo
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PushRequest {

    /**
     * 消息模板代码
     */
    @NotEmpty(message = "消息模板不允许为空")
    private String msgTemplateCode;
    /**
     * 模板参数变量
     */
    private Map<String, Object> extras;

}
