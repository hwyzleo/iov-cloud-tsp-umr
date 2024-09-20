package net.hwyz.iov.cloud.tsp.umr.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.umr.service.domain.contract.enums.PushTargetType;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExClientService;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExMpPushService;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository.MpMsgTemplateRepository;
import org.springframework.stereotype.Service;

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
     */
    public void pushSingleMobile(String accountId, String msgTemplateCode) {
        logger.info("使用模板[{}]推送账号[{}]手机", msgTemplateCode, accountId);
        mpMsgTemplateRepository.getByCode(msgTemplateCode).ifPresent(mpMsgTemplateDo -> {
            ClientResponse mpClient = exClientService.getMpClient(accountId);
            if (mpClient != null) {
                exMpPushService.push(PushTargetType.REG_ID, Set.of(mpClient.getPushRegId()), mpMsgTemplateDo.getNotification(),
                        mpMsgTemplateDo.getMessage());
            }
        });
    }

}
