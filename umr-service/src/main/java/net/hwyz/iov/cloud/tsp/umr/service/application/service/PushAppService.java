package net.hwyz.iov.cloud.tsp.umr.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.umr.service.domain.contract.enums.PushTargetType;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExClientService;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExMpPushService;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository.MpMsgTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 推送相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PushAppService {

    private final ExClientService exClientService;
    private final ExMpPushService exMpPushService;
    private final MpMsgTemplateRepository mpMsgTemplateRepository;

    /**
     * 推送单个手机
     *
     * @param accountId       账号ID
     * @param msgTemplateCode 消息模板代码
     * @param extras          消息额外参数
     */
    public void pushSingleMobile(String accountId, String msgTemplateCode, Map<String, Object> extras) {
        logger.info("使用模板[{}]推送账号[{}]手机", msgTemplateCode, accountId);
        mpMsgTemplateRepository.getByCode(msgTemplateCode).ifPresent(mpMsgTemplateDo -> {
            ClientResponse mpClient = exClientService.getMpClient(accountId);
            if (mpClient != null) {
                if (mpMsgTemplateDo.getMessage() != null) {
                    mpMsgTemplateDo.getMessage().setExtras(replaceExtras(mpMsgTemplateDo.getMessage().getExtras(), extras));
                }
                exMpPushService.push(PushTargetType.REG_ID, Set.of(mpClient.getPushRegId()), mpMsgTemplateDo.getNotification(),
                        mpMsgTemplateDo.getMessage());
            }
        });
    }

    /**
     * 替换消息模板中的占位符
     *
     * @param extrasTemplate 附加信息模板
     * @param extrasValue    附加信息值
     * @return 替换后的附加信息
     */
    private Map<String, Object> replaceExtras(Map<String, Object> extrasTemplate, Map<String, Object> extrasValue) {
        if (extrasTemplate != null && extrasValue != null) {
            Map<String, Object> newExtras = new HashMap<>();
            for (String key : extrasTemplate.keySet()) {
                if (extrasValue.containsKey(key)) {
                    newExtras.put(key, extrasValue.get(key));
                }
            }
            if (!newExtras.isEmpty()) {
                return newExtras;
            }
        }
        return null;
    }

}
