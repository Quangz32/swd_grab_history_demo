package com.grab.history;

import java.util.ArrayList;
import java.util.List;

public class Client implements IObservable {

  private final String name;
  private final String phone;
  private final List<Order> orders;
  private final List<IObserver> observers;

  public Client(String name, String phone) {
    this.name = name;
    this.phone = phone;
    this.orders = new ArrayList<>();
    this.observers = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public Order createOrder(String type) {
    Order order = new Order(type);
    order.setClient(this);
    orders.add(order);
    notifyObservers();
    return order;
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