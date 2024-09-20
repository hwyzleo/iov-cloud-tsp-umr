package net.hwyz.iov.cloud.tsp.umr.api.feign.service;

import net.hwyz.iov.cloud.tsp.umr.api.contract.request.SingleMobilePushRequest;

/**
 * 推送相关服务接口
 *
 * @author hwyz_leo
 */
public interface PushServiceApi {

    /**
     * 向单个手机端推送消息
     *
     * @param request 单个手机推送请求
     */
    void pushMobile(SingleMobilePushRequest request);

}
