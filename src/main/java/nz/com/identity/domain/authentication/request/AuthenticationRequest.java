package nz.com.identity.domain.authentication.request;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest
{
    public static final String SCOPE = "scope";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URL = "redirect_uri";
    public static final String STATE = "state";
    public static final String DISPLAY = "display";

    @NotNull
    private String scope;
    @NotNull
    private String responseType;
    @NotNull
    private String clientId;
    @NotNull
    private String redirectUrl;
    @NotNull
    private String state;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(
        String scope,
        String responseType,
        String clientId,
        String redirectUrl,
        String state
    ) {
        this.scope = scope;
        this.responseType = responseType;
        this.clientId = clientId;
        this.redirectUrl = redirectUrl;
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
