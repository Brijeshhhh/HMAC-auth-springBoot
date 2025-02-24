

# 🔐 HMAC Authentication in Spring Boot  

## 🛡️ What is HMAC?  
**HMAC (Hash-based Message Authentication Code)** is a cryptographic technique used to ensure **message integrity and authentication**. It combines a **secret key** with a **hash function** (e.g., SHA-256) to generate a secure message signature.  

## 🎯 Why Use HMAC?  
1. **Message Integrity** – Prevents data tampering in transit.  
2. **Authentication** – Ensures the request comes from a trusted source.  
3. **Security** – Even if data is intercepted, the HMAC cannot be forged without the secret key.  

---

## 🔄 How HMAC Works  

### 📌 **HMAC Generation Process**
1️⃣ Client sends a **message** (e.g., employee salary).  
2️⃣ The server generates an **HMAC signature** using a **secret key** and SHA-256.  
3️⃣ The hashed value (HMAC) is returned instead of the plain text.  

### 📌 **HMAC Verification Process**
1️⃣ The client provides both the original message and its **HMAC signature**.  
2️⃣ The server **recomputes** the HMAC using the secret key.  
3️⃣ If the computed HMAC matches the received HMAC, the data is **valid**.  

---

## 📊 Diagram  

### **HMAC Generation**  
```
   +------------------+
   |   Input Data     |  (Employee Salary)
   +------------------+
            |
            v
   +------------------+
   | Secret Key (hmac)|  (Shared between client & server)
   +------------------+
            |
            v
   +------------------+
   |  HMAC Function   |  (HmacSHA256)
   +------------------+
            |
            v
   +------------------+
   |   HMAC Output    |  (Secure Hashed Value)
   +------------------+
```

### **HMAC Verification**  
```
   +------------------+
   | Received Data    |  (Employee Salary)
   +------------------+
            |
            v
   +------------------+
   | Secret Key (hmac)|  (Known only to server)
   +------------------+
            |
            v
   +------------------+
   | Recompute HMAC   |  (Using same function)
   +------------------+
            |
            v
   +------------------+
   |  Compare HMACs   |  (Matches? Valid!)
   +------------------+
```

![image](https://github.com/user-attachments/assets/06ad410c-31bd-4623-a281-3ba0e061f5c3)

---

## 🚀 Features  

✅ **HMAC Authentication** – Ensures data integrity & security  
✅ **Spring Boot REST API** – Provides secure endpoints  
✅ **Custom HMAC Generation & Verification**  
✅ **Postman Testing URLs** – Ready-to-test API endpoints  

---

## 🏗️ Functionality  

This **Spring Boot** application implements **HMAC authentication** for **securing salary data**.  

- **Endpoint 1: Generate HMAC for Employee Salary**  
  - Receives employee details (name, salary).  
  - Returns employee name and **HMAC hashed salary**.  

- **Endpoint 2: Verify HMAC Signature**  
  - Takes **original salary and HMAC signature** as input.  
  - Recomputes HMAC and **checks if it matches** the provided signature.  

---

## 🛠️ Tech Stack  

| Dependency    | Purpose |
|--------------|---------|
| **Spring Boot** | Framework for building Java web applications |
| **Spring Web** | Enables RESTful API development |
| **Lombok** | Reduces boilerplate code for model classes |
| **HMAC SHA-256** | Cryptographic hash function for message authentication |

---

## 📦 Installation  

### **Step 1: Clone the Repository**  
```bash
git clone https://github.com/your-username/HMAC-auth-springBoot.git
cd HMAC-auth-springBoot
```

### **Step 2: Run the Application**  
```bash
mvn spring-boot:run
```

---

## 🌐 API Endpoints  

### **📌 1. Generate HMAC for Employee Salary**  
**Endpoint:**  
```http
POST /api/employee
```

**Request Body (JSON Example):**  
```json
{
  "empName": "John Doe",
  "empSalary": "50000"
}
```

**Response:**  
```json
{
  "empName": "John Doe",
  "empSalary": "c4f27f3d2d2d847e2a..."
}
```

---

### **📌 2. Verify HMAC Signature**  
**Endpoint:**  
```http
GET /api/verify
```

**Query Parameters:**  
```http
/api/verify?salary=50000&hmac=c4f27f3d2d2d847e2a...
```

**Response (If valid):**  
```json
true
```

**Response (If invalid):**  
```json
false
```

---

## 🖥️ Postman Testing URLs  

1. **Generate HMAC for Employee Salary**  
   - Method: `POST`  
   - URL: `http://localhost:8080/api/employee`  
   - Body:  
     ```json
     {
       "empName": "John Doe",
       "empSalary": "50000"
     }
     ```

2. **Verify HMAC Signature**  
   - Method: `GET`  
   - URL:  
     ```
     http://localhost:8080/api/verify?salary=50000&hmac=<generated-hmac>
     ```

---

## 🚀 Future Enhancements   
🔹 **Role-Based Access Control (Spring Security)**  
