package net.hwyz.iov.cloud.tsp.umr.service.infrastructure.external;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.umr.service.domain.contract.enums.PushTargetType;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExMpPushService;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MessageVo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.NotificationVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 外部手机推送相关服务极光SP实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
public class JiguangMpPushServiceImpl implements ExMpPushService {

    /**
     * App Key
     */
    @Value("${sp.jpush.app-key}")
    private String appKey;
    /**
     * Master Secret
     */
    @Value("${sp.jpush.master-secret}")
    private String masterSecret;
    /**
     * 接口调用地址
     */
    private final String url = "https://api.jpush.cn/v3/push";

    @Override
    public void push(PushTargetType pushTargetType, Set<String> pushTargetSet, NotificationVo notification, MessageVo message) {
        logger.debug("调用极光推送[{}]目标[{}]", pushTargetType, pushTargetSet.size());
        HttpRequest post = HttpUtil.createPost(url);
        post.header("Authorization", buildAuthorization());
        post.body(buildBody(pushTargetType, pushTargetSet, notification, message));
        HttpResponse response = post.execute();
        logger.debug("极光推送响应[{}]", response.body());
    }

    /**
     * 构建Authorization
     *
     * @return Authorization
     */
    private String buildAuthorization() {
        return "Basic " + Base64.encode((this.appKey + ":" + this.masterSecret));
    }

    /**
     * 构建请求体
     *
     * @return 请求体
     */
    private String buildBody(PushTargetType pushTargetType, Set<String> pushTargetSet, NotificationVo notification, MessageVo message) {
        Map<String, Object> body = new HashMap<>();
        body.put("platform", "all");
        Map<String, Object> audience = new HashMap<>();
        switch (pushTargetType) {
            case ALL -> body.put("audience", "all");
            case TAG -> {
                audience.put("tag", pushTargetSet);
                body.put("audience", audience);
            }
            case ALIAS -> {
                audience.put("alias", pushTargetSet);
                body.put("audience", audience);
            }
            case REG_ID -> {
                audience.put("registration_id", pushTargetSet);
                body.put("audience", audience);
            }
        }
        if (notification != null) {
            body.put("notification", notification);
        }
        if (message != null) {
            body.put("message", message);
        }
        return JSONUtil.toJsonStr(body);
    }

}
