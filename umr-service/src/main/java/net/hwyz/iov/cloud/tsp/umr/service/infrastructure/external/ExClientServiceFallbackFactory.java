package net.hwyz.iov.cloud.tsp.umr.service.infrastructure.external;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.ClientResponse;
import net.hwyz.iov.cloud.tsp.umr.service.domain.external.service.ExClientService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * 外部客户端业务服务回退处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
public class ExClientServiceFallbackFactory implements FallbackFactory<ExClientService> {

    @Override
    public ExClientService create(Throwable cause) {
        return new ExClientService() {

            @Override
            public ClientResponse getMpClient(String accountId) {
                if (logger.isDebugEnabled()) {
                    logger.warn("获取账号最新手机客户端异常", cause);
                } else {
                    logger.warn("获取账号最新手机客户端异常:[{}]", cause.getMessage());
                }
                return null;
            }

        };
    }

}
