package bg.sofia.tu.rsai.communication.storage;

import bg.sofia.tu.rsai.communication.Message;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 08/06/2017 @ 23:04.
 */
@Service
public class StorageService {

    private static final ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<>();

    public Object retrieveData(String key) {
        return data.get(key);
    }

    public Object putData(String key, Object value) {
        return data.put(key, value);
    }

    public List<Message> retrieveMessages() {
        List<Message> result = new LinkedList<>();

        for(Map.Entry<String,Object> entry : data.entrySet()) {
            Message message = new Message();
            message.setModuleName(entry.getKey());
            message.setValue(entry.getValue());

            result.add(message);
        }
        return result;
    }
}
