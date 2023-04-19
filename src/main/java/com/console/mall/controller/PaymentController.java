//package com.console.mall.controller;
//
//import kr.co.bootpay.Bootpay;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("/payment")
//public class PaymentController {
//    private final Bootpay bootpay;
//
//    @Autowired
//    public PaymentController(Bootpay bootpay) {
//        this.bootpay = bootpay;
//    }
////    @PostConstruct
////    public void init() {
////        bootpay = new Bootpay("643ed04b755e27001ce57cdf", "5Q40Po8g2FHA/OJ+E+FAZAJ4A6VGVXlFZ781hV1Ob+I=");
////    }
//
//    @PostMapping("/payment/verify")
//    public String verifyPayment(@RequestParam String receiptId) {
//        try {
//            HashMap<String, Object> res = bootpay.getAccessToken();
//            if (res.get("error_code") == null) {
//                HashMap<String, Object> verifyResult = bootpay.verify(receiptId);
//                if (verifyResult.get("status") != null && verifyResult.get("status").equals(1)) {
//                    // 결제 검증 성공
//                    return "success";
//                } else {
//                    // 결제 검증 실패
//                    return "failed";
//                }
//            } else {
//                return "failed";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }
//}
