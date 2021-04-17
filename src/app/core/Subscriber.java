package app.core;

public interface Subscriber {

    public void onUpdate();
    public void subscribe(Publisher publisher);
    public void unsubscribe(Publisher publisher);

}
