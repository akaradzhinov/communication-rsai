package bg.sofia.tu.rsai.communication.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 08/06/2017 @ 00:14.
 */

//[Requirement SRSReq03] Да има имплементирана основна стратегия за възстановяване чрез повторно изпращане на съобщенията при грешка
public class CommunicationErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus.Series series = response.getStatusCode().series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Response error: " + response.getStatusCode() + " "+ response.getStatusText());
    }
}
