package bg.sofia.tu.rsai.communication;

import bg.sofia.tu.rsai.communication.mappings.ModuleMappings;
import bg.sofia.tu.rsai.communication.response.CommunicationResponse;
import bg.sofia.tu.rsai.communication.rest.CommunicationRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 07/06/2017 @ 23:47.
 */
@Service
public class CommunicationService {

    @Autowired
    private CommunicationRestTemplate communicationRestTemplate;


    //[Requirement SRSReq02] Изпраща съобщения (прави POST request) до визуализиращия екип
    public CommunicationResponse sendMessage(Message message) {
        String url = ModuleMappings.getValue(message.getModuleName());

        return this.communicationRestTemplate.post(url, message);
    }
}
