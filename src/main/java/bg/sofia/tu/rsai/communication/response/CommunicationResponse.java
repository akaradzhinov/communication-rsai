package bg.sofia.tu.rsai.communication.response;

import org.springframework.http.HttpStatus;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 08/06/2017 @ 00:37.
 */
public class CommunicationResponse {

    private HttpStatus status;

    private String message;

    public CommunicationResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommunicationResponse{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
