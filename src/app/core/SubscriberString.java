package app.core;

public interface SubscriberString {

    public void subscribe(PublisherString publisher);
    public void unsubscribe(PublisherString publisher);
    public void update(String string);

}
