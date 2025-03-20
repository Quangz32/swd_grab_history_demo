package com.grab.history;

import java.util.ArrayList;
import java.util.List;

public class Driver implements IObservable {

  private final String name;
  private final String phone;
  private Order currentOrder;
  private final List<IObserver> observers;

  public Driver(String name, String phone) {
    this.name = name;
    this.phone = phone;
    this.observers = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public Order getCurrentOrder() {
    return currentOrder;
  }

  public void confirmOrder(Order order) {
    this.currentOrder = order;
    order.setDriver(this);
    order.setStatus("CONFIRMED");
    notifyObservers();
  }

  public void pickupClient(Order order) {
    order.setStatus("PICKED_UP");
    notifyObservers();
  }

  public void finishOrder(Order order) {
    order.setStatus("COMPLETED");
    notifyObservers();
    this.currentOrder = null;
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