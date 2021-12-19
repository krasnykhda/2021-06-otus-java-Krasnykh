package ru.otus.MessageSystemAPP.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.RequestHandler;
import ru.otus.client.ResultDataType;
import ru.otus.message.Message;

import java.util.Optional;

public class GetUserDataResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

    @Override
    public <T extends ResultDataType> Optional<Message<T>> handle(Message<T> msg) {
        logger.info("new message:{}", msg);
        try {
            var callback = msg.getCallback();
            if (callback != null) {
                callback.accept(msg.getData());
            } else {
                logger.error("callback for Id:{} not found", msg.getId());
            }
        } catch (Exception ex) {
            logger.error("msg:{}", msg, ex);
        }
        return Optional.empty();
    }
}
