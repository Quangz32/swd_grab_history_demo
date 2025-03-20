package com.grab.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryController implements IObserver {

  private final List<Client> clients;
  private final List<Driver> drivers;
  private final Map<Integer, String> lastOrderStatus;

  public HistoryController() {
    this.clients = new ArrayList<>();
    this.drivers = new ArrayList<>();
    this.lastOrderStatus = new HashMap<>();
  }

  public void registerClient(Client client) {
    clients.add(client);
    client.registerObserver(this);
  }

  public void registerDriver(Driver driver) {
    drivers.add(driver);
    driver.registerObserver(this);
  }

  public void addHistoryEntry(Order order, HistoryEntry he) {
    //...
  }

  @Override
  public void update() {
    // Kiểm tra các order mới từ client
    for (Client client : clients) {
      List<Order> orders = client.getOrders();
      if (!orders.isEmpty()) {
        Order lastOrder = orders.get(orders.size() - 1);
        if (!lastOrderStatus.containsKey(lastOrder.getId())) {
          String message = String.format("Khách hàng %s đã đặt chuyến", client.getName());
          lastOrder.addHistoryEntry(new HistoryEntry(message));
          lastOrderStatus.put(lastOrder.getId(), lastOrder.getStatus());
        }
      }
    }

    // Kiểm tra các order từ driver
    for (Driver driver : drivers) {
      Order currentOrder = driver.getCurrentOrder();
      if (currentOrder != null) {
        String currentStatus = currentOrder.getStatus();
        String lastStatus = lastOrderStatus.get(currentOrder.getId());

        if (!currentStatus.equals(lastStatus)) {
          String message = "";
          switch (currentStatus) {
            case "CONFIRMED":
              message = String.format("Tài xế %s đã nhận chuyến của khách hàng %s",
                  driver.getName(), currentOrder.getClient().getName());
              break;
            case "PICKED_UP":
              message = String.format("Tài xế %s đã đón được khách %s",
                  driver.getName(), currentOrder.getClient().getName());
              break;
            case "COMPLETED":
              message = "Chuyến đi đã hoàn thành";
              break;
          }
          currentOrder.addHistoryEntry(new HistoryEntry(message));
          lastOrderStatus.put(currentOrder.getId(), currentStatus);
        }
      }
    }
  }
} 