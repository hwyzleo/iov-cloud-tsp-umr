package net.hwyz.iov.cloud.tsp.umr.api.contract.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SingleMobilePushRequest extends PushRequest {

    /**
     * 账号ID
     */
    @NotEmpty(message = "账号ID不允许为空")
    private String accountId;

}
