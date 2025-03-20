package com.grab.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Order implements IObservable {
    private int id;
    private String type;
    private String status;
    private Client client;
    private Driver driver;
    private List<IObserver> observers;
    private List<HistoryEntry> historyEntries;

    public Order(String type) {
        this.id = (int) (1000000 * Math.random());
        this.type = type;
        this.status = "CREATED";
        this.observers = new ArrayList<>();
        this.historyEntries = new ArrayList<>();
    }

    public void addHistoryEntry(HistoryEntry entry) {
        historyEntries.add(entry);
        System.out.printf("Order [%d]: %s%n", id, entry);
    }

    public List<HistoryEntry> getHistoryEntries() {
        return historyEntries;
    }

    public int getId() {
        return id;
    }

    public void setClient(Client client) {
        this.client = client;
        notifyObservers();
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
        notifyObservers();
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public Driver getDriver() {
        return driver;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
} 