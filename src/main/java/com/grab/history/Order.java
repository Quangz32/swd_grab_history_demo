package com.grab.history;

import java.util.ArrayList;
import java.util.List;

public class Order implements IObservable {

  private final int id;
  private final String type;
  private String status;
  private Client client;
  private Driver driver;
  private final List<IObserver> observers;
  private final List<HistoryEntry> historyEntries;

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

  public String getType() {
    return type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
    notifyObservers();
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
    notifyObservers();
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
    notifyObservers();
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