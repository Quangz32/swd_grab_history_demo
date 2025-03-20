package com.grab.history;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    // Tạo history controller
    HistoryController historyController = new HistoryController();

    // Tạo khách hàng và tài xế
    Client client1 = new Client("Quang", "0123456789");
    Driver driver1 = new Driver("Hưng", "0987654321");

    Client client2 = new Client("Ngọc", "0123456789");
    Driver driver2 = new Driver("Đức", "0987654321");

    // Đăng ký client và driver với controller
    historyController.registerClient(client1);
    historyController.registerDriver(driver1);

    historyController.registerClient(client2);
    historyController.registerDriver(driver2);

    // Khách hàng tạo đơn
    Order order1 = client1.createOrder("BIKE");
    Thread.sleep(2000);
    Order order2 = client2.createOrder("CAR");

    // Mô phỏng các bước của chuyến đi
    Thread.sleep(2000);
    driver1.confirmOrder(order1);

    Thread.sleep(3000);
    driver2.confirmOrder(order2);

    Thread.sleep(2000);
    driver2.pickupClient(order2);

    Thread.sleep(2000);
    driver1.pickupClient(order1);

    Thread.sleep(3000);
    driver1.finishOrder(order1);

    Thread.sleep(2000);
    driver2.finishOrder(order2);


  }
} 