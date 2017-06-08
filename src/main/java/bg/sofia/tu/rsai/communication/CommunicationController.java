package bg.sofia.tu.rsai.communication;

import bg.sofia.tu.rsai.communication.response.CommunicationResponse;
import bg.sofia.tu.rsai.communication.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 07/06/2017 @ 22:57.
 */
@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private StorageService storageService;


    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        if (!validMessage(message)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("moduleName is null or empty!");
        }
        CommunicationResponse result;

        try {
            result = communicationService.sendMessage(message);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        storageService.putData(message.getModuleName(), message.getValue());
        return ResponseEntity.status(result.getStatus()).body(result.getMessage());
    }

    @RequestMapping(value = "/message/{key}")
    public Object getMessageByModule(@PathVariable String key) {
        return storageService.retrieveData(key);
    }

    @RequestMapping(value = "/messages")
    public List<Message> getAllMessages() {
        return storageService.retrieveMessages();
    }

    //only for testing CommunicationRestTemplate purposes
    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity test(@RequestBody Message message) {
        System.out.println("Got request: " + message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your request was pretty bad");
    }

    private boolean validMessage(Message message) {
        return !StringUtils.isEmpty(message.getModuleName());
    }
}
