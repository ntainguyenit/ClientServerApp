[English Below]

# Ứng dụng nhắn tin Client-Server

**Ứng dụng nhắn tin Client-Server được xây dựng bằng Java Sockets.**

Dự án này minh họa một ứng dụng phân tán đơn giản sử dụng **Java Sockets**.
Hệ thống cung cấp mô hình client-server, nơi server có thể xử lý nhiều client cùng lúc và các client có thể gửi/nhận tin nhắn theo thời gian thực.

---

## Tính năng

* Hỗ trợ nhiều client thông qua Socket Programming
* Giao diện GUI đơn giản (cấu trúc project NetBeans)
* Trao đổi tin nhắn thời gian thực giữa server và client
* Dễ dàng chạy thử và mở rộng để học lập trình phân tán

---

## Yêu cầu

* Java JDK 8 trở lên
* NetBeans IDE (hoặc bất kỳ Java IDE nào hỗ trợ Maven)

---

## Hướng dẫn chạy

### 1. Clone repository

```bash
git clone https://github.com/ntainguyenit/ClientServerApp.git
cd ClientServerApp
```

---

### 2. Mở project bằng NetBeans (hoặc IDE bạn muốn sử dụng)

---

### 3. Chạy Server trước

```bash
FServer.java
```

---

### 4. Chạy một hoặc nhiều Client

```bash
FClient.java
```

---

### 5. Bắt đầu nhắn tin giữa các Client thông qua Server

---

## Công nghệ sử dụng

* **Java**
* **Java Socket Programming**
* **Swing GUI**
* **Client-Server Architecture**
* **Distributed Programming**

---

## Ghi chú

* Dự án được xây dựng nhằm phục vụ học tập môn lập trình phân tán.
* Code được tổ chức đơn giản, dễ hiểu và dễ mở rộng.
* Có thể phát triển thêm:

  * Chat Room
  * File Transfer
  * Authentication
  * Database Integration

---

## Giấy phép

Dự án này được tạo cho mục đích học tập về lập trình phân tán với Java.

Bạn được tự do sử dụng, chỉnh sửa và chia sẻ.

---

# ClientServerApp

**Client-Server messaging app built with Java Sockets.**

This project demonstrates a simple distributed application using **Java Sockets**.
It provides a client-server model where the server can handle multiple clients, and clients can send/receive messages in real time.

---

## Features

* Multi-client support via socket programming
* Simple GUI (NetBeans project structure)
* Real-time message exchange between server and clients
* Easy to run and extend for learning distributed systems

---

## Requirements

* Java JDK 8 or higher
* NetBeans IDE (or any Java IDE with Maven support)

---

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/ntainguyenit/ClientServerApp.git
cd ClientServerApp
```

---

### 2. Open project in NetBeans (or your IDE of choice)

---

### 3. Run the server first

```bash
FServer.java
```

---

### 4. Run one or more client instances

```bash
FClient.java
```

---

### 5. Start messaging between Clients via the Server

---

## Technologies Used

* **Java**
* **Java Socket Programming**
* **Swing GUI**
* **Client-Server Architecture**
* **Distributed Programming**

---

## Notes

* This project was created for learning distributed programming concepts.
* The codebase is organized simply for easy understanding and extension.
* Future improvements may include:

  * Chat Room
  * File Transfer
  * Authentication
  * Database Integration

---

## License

This project is created for learning purposes in distributed programming with Java.

You are free to use, modify, and share
