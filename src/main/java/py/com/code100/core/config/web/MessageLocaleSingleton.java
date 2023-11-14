package py.com.code100.core.config.web;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MessageLocaleSingleton {

    @Getter
    private static MessageServiceLocale messageService;

    @Autowired
    public void setMessageService(MessageServiceLocale service) {
        messageService = service;
    }

}
