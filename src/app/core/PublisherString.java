package app.core;

public interface PublisherString {

    public void addSubscriber(SubscriberString subscriber);
    public void removeSubscriber(SubscriberString subscriber);
    public void notifySubscribers(String string);

}
