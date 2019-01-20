package nz.com.identity.domain.user.requests;

import nz.com.identity.domain.common.requests.BaseRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCreateRequest extends BaseRequest {

    @NotNull
    @Email
    public String email;

    @NotNull
    @Size(min = 6, max = 16)
    public String password;

    @NotNull
    @Size(min = 6, max = 16)
    public String passwordConfirm;

    @Size(min = 3, max = 32)
    public String firstName;

    @Size(min = 3, max = 32)
    public String lastName;

    @Size(min = 3, max = 32)
    public String patronymic;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
