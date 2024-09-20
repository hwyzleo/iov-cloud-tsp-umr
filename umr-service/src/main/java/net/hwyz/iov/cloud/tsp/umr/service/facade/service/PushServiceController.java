package net.hwyz.iov.cloud.tsp.umr.service.facade.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.umr.api.contract.request.SingleMobilePushRequest;
import net.hwyz.iov.cloud.tsp.umr.api.feign.service.PushServiceApi;
import net.hwyz.iov.cloud.tsp.umr.service.application.service.PushAppService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/push")
public class PushServiceController implements PushServiceApi {

    private final PushAppService pushAppService;

    /**
     * 向单个手机端推送消息
     *
     * @param request 单个手机推送请求
     */
    @Override
    @PostMapping("/singleMobile")
    public void pushMobile(@RequestBody @Valid SingleMobilePushRequest request) {
        logger.info("向账号[{}]手机推送消息模板[{}]", request.getAccountId(), request.getMsgTemplateCode());
        pushAppService.pushSingleMobile(request.getAccountId(), request.getMsgTemplateCode());
    }

}
