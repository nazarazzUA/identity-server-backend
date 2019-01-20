package nz.com.identity.domain.client.requests;

import nz.com.identity.domain.common.requests.BaseRequest;

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

    protected String allowedDomain;
    protected String allowedRedirectUrls;
    protected String allowedClientIps;

    public ClientRequest() { }

    public ClientRequest(@NotNull String clientId, @NotNull String clientIp, @NotNull String userAgent, String clientSecret, @NotNull @Size(min = 6, max = 16) String clientId1, @NotNull @Size(min = 24, max = 512) String clientSecret1, @NotNull @Max(ONE_YEAR_IN_SECOND) int accessTokenTtl, @NotNull @Max(ONE_YEAR_IN_SECOND) int refreshTokenTtl, String allowedDomain, String allowedRedirectUrls, String allowedClientIps) {
        super(clientId, clientIp, userAgent, clientSecret);
        this.clientId = clientId1;
        this.clientSecret = clientSecret1;
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;
        this.allowedDomain = allowedDomain;
        this.allowedRedirectUrls = allowedRedirectUrls;
        this.allowedClientIps = allowedClientIps;
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
