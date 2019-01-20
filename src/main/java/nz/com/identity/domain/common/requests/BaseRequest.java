package nz.com.identity.domain.common.requests;

import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.NotNull;

public class BaseRequest {

    private static final int MAX_USER_AGENT_LENGTH = 512;

    @NotNull
    protected String clientId;

    protected String clientIp;

    protected String userAgent;

    protected String clientSecret;

    public BaseRequest() {}

    public BaseRequest(String clientId, String clientIp, @RequestHeader(value = "User-Agent") String userAgent) {
        this.clientId = clientId;
        this.clientIp = clientIp;

        if (userAgent != null && userAgent.length() > MAX_USER_AGENT_LENGTH) {
            this.userAgent = userAgent.trim().substring(0, MAX_USER_AGENT_LENGTH);
        } else {
            this.userAgent = userAgent;
        }
    }

    public BaseRequest(@NotNull String clientId, @NotNull String clientIp, @NotNull String userAgent, String clientSecret) {
        this(clientId, clientIp, userAgent);
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
