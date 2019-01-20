package nz.com.identity.domain.client;

import nz.com.identity.domain.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client extends BaseEntity {

    @Column
    protected String clientId;
    @Column
    protected String clientSecret;
    @Column
    protected String allowedDomain;
    @Column
    protected String allowedRedirectUrls;
    @Column
    protected String allowedClientIps;
    @Column
    protected int accessTokenTtl;
    @Column
    protected int refreshTokenTtl;

    public Client() { }

    public Client(
        String clientId,
        String clientSecret,
        String allowedDomain,
        String allowedRedirectUrls,
        String allowedClientIps,
        int accessTokenTtl,
        int refreshTokenTtl
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.allowedDomain = allowedDomain;
        this.allowedRedirectUrls = allowedRedirectUrls;
        this.allowedClientIps = allowedClientIps;
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
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

    public int getAccessTokenTtl() {
        return accessTokenTtl;
    }

    public int getRefreshTokenTtl() {
        return refreshTokenTtl;
    }
}
