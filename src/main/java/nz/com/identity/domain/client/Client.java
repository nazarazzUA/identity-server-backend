package nz.com.identity.domain.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nz.com.identity.domain.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client extends BaseEntity {

    public final static String PUBLIC = "public";
    public final static String CONFIDENTIAL = "confidential";

    @Column(nullable = false)
    protected String clientId;
    @Column()
    @JsonIgnore
    protected String clientSecret;

    @Column(nullable = false)
    protected String clientType;
    @Column
    protected String allowedDomain;
    @Column
    protected String allowedRedirectUrls;
    @Column
    protected String allowedClientIps;
    @Column(nullable = false)
    protected int accessTokenTtl;
    @Column(nullable = false)
    protected int refreshTokenTtl;

    public Client() { }

    public Client(
        String clientId,
        String clientSecret,
        String clientType,
        String allowedDomain,
        String allowedRedirectUrls,
        String allowedClientIps,
        int accessTokenTtl,
        int refreshTokenTtl
    ) {
        this.clientId = clientId;
        this.clientType = clientType;
        this.clientSecret = clientSecret;
        this.allowedDomain = allowedDomain;
        this.allowedRedirectUrls = allowedRedirectUrls;
        this.allowedClientIps = allowedClientIps;
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setAllowedDomain(String allowedDomain) {
        this.allowedDomain = allowedDomain;
    }

    public void setAllowedRedirectUrls(String allowedRedirectUrls) {
        this.allowedRedirectUrls = allowedRedirectUrls;
    }

    public void setAllowedClientIps(String allowedClientIps) {
        this.allowedClientIps = allowedClientIps;
    }

    public void setAccessTokenTtl(int accessTokenTtl) {
        this.accessTokenTtl = accessTokenTtl;
    }

    public void setRefreshTokenTtl(int refreshTokenTtl) {
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

    public String getClientType() {
        return clientType;
    }
}
