package app.ErrorHandler;

import app.core.PublisherString;
import app.core.SubscriberString;

import java.util.ArrayList;

public class ErrorHandlerImpl implements ErrorHandlerInterface, PublisherString {

    private ArrayList<SubscriberString> subscribers = new ArrayList<SubscriberString>();


    @Override
    public void throwException(String text) {

        notifySubscribers(text);

    }

    @Override
    public void addSubscriber(SubscriberString subscriber) {
        if(!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(SubscriberString subscriber) {
        if(subscribers.contains(subscriber))
            subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String string) {
        for(SubscriberString s:subscribers)
            s.update(string);
    }
}
