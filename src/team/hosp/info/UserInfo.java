package team.hosp.info;

/**
 * Created by hasuka on 2017/7/8.
 */
public class UserInfo {
    private String account;
    private String password;

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
