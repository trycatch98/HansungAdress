package trycatch.hs.hansungadress.event;

/**
 * Created by trycatch on 2017. 11. 28..
 */

public class LoginEvent {
    boolean isLogin;

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }
}
