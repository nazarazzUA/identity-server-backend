package nz.com.identity.domain.user.requests;

import nz.com.identity.domain.common.requests.BaseRequest;
import javax.validation.constraints.*;

public class UserCredentialsRequest extends BaseRequest {

    @NotNull
    @Email
    @Size(min = 2, max = 52)
    private String username;

    @NotNull
    @Size(min = 6, max = 16)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
