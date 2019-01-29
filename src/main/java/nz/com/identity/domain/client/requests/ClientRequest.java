package nz.com.identity.domain.client.requests;

import nz.com.identity.domain.common.requests.BaseRequest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientRequest extends BaseRequest {

    private static final long ONE_YEAR_IN_SECOND = 31556926L;

    @NotNull
    @Max(ONE_YEAR_IN_SECOND)
    protected int accessTokenTtl;

    @NotNull
    @Max(ONE_YEAR_IN_SECOND)
    protected int refreshTokenTtl;

    @NotNull
    protected String clientType;

    protected String allowedDomain;
    protected String allowedRedirectUrls;
    protected String allowedClientIps;

    public ClientRequest() { }

    public ClientRequest(
            @NotNull String clientId,
            @NotNull String clientType,
            @NotNull String clientIp,
            @NotNull String userAgent,
            @NotNull @Max(ONE_YEAR_IN_SECOND) int accessTokenTtl,
            @NotNull @Max(ONE_YEAR_IN_SECOND) int refreshTokenTtl,
            String allowedDomain,
            String allowedRedirectUrls,
            String allowedClientIps
    ) {
        super(clientId, clientIp, userAgent);
        this.clientType = clientType;
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;
        this.allowedDomain = allowedDomain;
        this.allowedRedirectUrls = allowedRedirectUrls;
        this.allowedClientIps = allowedClientIps;
    }

    public String getClientType() {
        return clientType;
    }

    public int getAccessTokenTtl() {
        return accessTokenTtl;
    }

    public int getRefreshTokenTtl() {
        return refreshTokenTtl;
    }

    public String getAllowedDomain() {
        return allowedDomain;
    }

    public String getAllowedRedirectUrls() {
        return allowedRedirectUrls;
    }

    public String getAllowedClientIps() {
        return allowedClientIps;
    }
}
