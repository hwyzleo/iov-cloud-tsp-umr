package net.hwyz.iov.cloud.tsp.umr.service.domain.external.service;

import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.umr.service.infrastructure.external.ExClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 外部客户端业务服务
 *
 * @author hwyz_leo
 */
@FeignClient(name = "account-service", path = "/service/client", fallbackFactory = ExClientServiceFallbackFactory.class)
public interface ExClientService {

    /**
     * 获取账号最新手机客户端
     *
     * @param accountId 账号ID
     * @return 客户端信息
     */
    @GetMapping(value = "/mp")
    ClientResponse getMpClient(@RequestParam String accountId);

}
