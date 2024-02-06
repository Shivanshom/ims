package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import com.electrowaveselectronics.inventorymanagement.repository.AuthRepository;
import com.electrowaveselectronics.inventorymanagement.service.GodownHeadService;
import com.electrowaveselectronics.inventorymanagement.service.GodownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GodownHeadController {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    GodownService godownService;
    private GodownHeadService godownHeadService;

    @Autowired
    public GodownHeadController(GodownHeadService godownHeadService) {
        this.godownHeadService = godownHeadService;
    }

    @GetMapping("/getAllGodownHead")
    public List<GodownHead> findAll(@CookieValue(value = "token") String token) throws Exception {
        return godownHeadService.findAll();
    }

    @GetMapping("/getGodownHead/{GodownHeadId}")
    public GodownHead getGodownHead(@PathVariable int GodownHeadId,@CookieValue(value = "token") String token) throws Exception {
        GodownHead theGodownHead = godownHeadService.getGodownHead(GodownHeadId);
        if (theGodownHead == null) {
            throw new RuntimeException("GodownHead id not found= " + GodownHeadId);
        }
        return theGodownHead;
    }

    @PostMapping("/setGodownHead")
    public GodownHead addGodownHead(@RequestBody GodownHead theGodownHead,@CookieValue(value = "token") String token) {
        theGodownHead.setGodownHeadId(0);
        return godownHeadService.save(theGodownHead);
    }

    @PutMapping("/updateGodownHead")
    public ResponseEntity<?> updateGodownHead(@RequestBody GodownHead theGodownHead,@CookieValue(value = "token") String token) throws Exception {
        try {
            return new ResponseEntity<>(godownHeadService.updateGodownHead(theGodownHead), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.NOT_FOUND);
        }
    }
//
//    private Cookie generateToken(String username) {
//        Cookie cookie = new Cookie("user",username);
//        return cookie;
//    }
//
//    @PostMapping("/loginwithPassword")
//    @ResponseBody
//    public ResponseEntity<?> loginwithPassword(@RequestBody HashMap<String, String> data, HttpServletResponse response) {
//        HashMap<String, String> result = new HashMap<>();
//        try {
//            result = godownHeadService.loginwithPassword(data.get("username"), data.get("password"));
//            if (result.containsKey("success")) {
//                Cookie token = generateToken(data.get("username")); // Replace with actual token generation logic
//                result.put("message", "Successfully logged in.");
//                godownHeadService.setAuthToken(token.toString(), data.get("username"));
//                response.addCookie(token);
//                return ResponseEntity.accepted().header("Set-Cookie", "token=" + token).body(result);
//            } else {
//                return ResponseEntity.badRequest().body(result);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//
//    @GetMapping("api/listProducts/{godownId}")
//    @ResponseBody
//    public ResponseEntity<?> listProductByGodownId(@PathVariable int godownId){
//        try{
//            List<Product> productList = godownService.listProductByGodownId(godownId);
//            if(!Objects.isNull(productList)){
//                return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
//            }
//            else{
//                return new ResponseEntity<>("No products Found "+godownId, HttpStatus.NOT_FOUND);
//            }
//
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/api/setGodown")
//    public ResponseEntity<?> setGodown(@RequestBody Godown theGodown) {
//
//        try {
//            Godown newGodown = godownService.setGodown(theGodown);
//            if (!Objects.isNull(newGodown)) {
//                return new ResponseEntity<>(newGodown, HttpStatus.ACCEPTED);
//            } else {
//
//                return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PatchMapping("api/addProduct/{godownId}")
//    public ResponseEntity<?> addProductByGodownId(@RequestBody Product theproduct, @PathVariable int godownId) {
//        try {
//            Product newProduct = godownService.addProductByGodownId(godownId, theproduct);
//            if (!Objects.isNull(newProduct)) {
//                return new ResponseEntity<>("Product added "+ newProduct.getProductName(), HttpStatus.ACCEPTED);
//            } else {
//
//                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}

