package app.core;

public interface Publisher {

    public void notifySubscribers();
    public void addSubscriber(Subscriber subscriber);
    public void removeSubscriber(Subscriber subscriber);

}
