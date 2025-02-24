package com.excelacom.HmacAuthApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
class EmployeeController {

    private static final String SECRET_KEY = "hmac";

    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> createEmployee(@RequestBody Employee employee) {
        try {
            String salary = employee.getEmpSalary();

            if (salary == null || salary.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            String hmacSalary = generateHMAC(salary, SECRET_KEY);

            Map<String, String> response = new HashMap<>();
            response.put("empName", employee.getEmpName());
            response.put("empSalary", hmacSalary);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyHMAC(@RequestParam String salary, @RequestParam String hmac) {
        try {
            String calculatedHmac = generateHMAC(salary, SECRET_KEY);
            boolean isValid = calculatedHmac.equals(hmac);
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String generateHMAC(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hmacBytes = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hmacBytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
