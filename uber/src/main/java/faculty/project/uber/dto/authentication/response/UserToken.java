package faculty.project.uber.dto.authentication.response;

public class UserToken {
    private String accessToken;


    public UserToken() {
        this.accessToken = null;

    }

    public UserToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
