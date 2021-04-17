package app.repository.node;


import app.core.Publisher;
import app.core.Subscriber;
import app.gui.swing.view.MainFrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public abstract class RuNode implements Comparable<RuNode>, Publisher, Serializable {

    transient private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
    private static int number = 1;
    private int verificationNumber;
    private String name;
    private RuNode parent;
    private static final long serialVersionUID = 278784196;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
        verificationNumber = number;
        number = number+1;

    }

    public RuNode(RuNode parent){
        this.parent = parent;
        this.name = addName();
        verificationNumber = number;
        number = number+1;

    }

    protected abstract String addName();



    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RuNode) {
            RuNode otherObj = (RuNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    @Override
    public int compareTo(RuNode node) {
        return this.getName().compareTo(node.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifySubscribers();
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    public int getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(int verificationNumber) {
        this.verificationNumber = verificationNumber;
    }

    @Override
    public void notifySubscribers() {
        //for(Subscriber s:subscribers){
        //    s.onUpdate();
        //}
        for(int i=0; i<subscribers.size();i++){
            subscribers.get(i).onUpdate();
        }
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscribers == null)
            subscribers = new ArrayList<Subscriber>();
        if(subscriber!=null){
            subscribers.add(subscriber);
        }

    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber!=null){
            subscribers.remove(subscriber);
        }

    }

    public ArrayList<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void initSubs(){
        subscribers = new ArrayList<Subscriber>();
    }
}
