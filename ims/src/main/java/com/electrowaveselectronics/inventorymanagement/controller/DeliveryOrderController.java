package com.electrowaveselectronics.inventorymanagement.controller;
import com.electrowaveselectronics.inventorymanagement.dto.DeliveryOrderDTO;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.service.AuthService;
import com.electrowaveselectronics.inventorymanagement.service.DeliveryOrderService;
import com.electrowaveselectronics.inventorymanagement.service.GodownHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "${myapp.cors.origin}", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class DeliveryOrderController {
    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Autowired
    private AuthService authService;

    @Autowired
    private GodownHeadService godownHeadService;

    //FOR ADMIN USE

    @GetMapping("/getDeliveryOrders")
    @ResponseBody
    public ResponseEntity<?> getAllDeliveryOrders(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                List<DeliveryOrder> deliveryOrders = deliveryOrderService.getAllDeliveryOrders();
                return new ResponseEntity<>(deliveryOrders, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDeliveryOrdersById/{id}")
    @ResponseBody
    public ResponseEntity<?> getDeliveryOrderById(@PathVariable int id,@RequestHeader("Authorization") String authorizationHeader) {

        try {
                String token = extractTokenFromAuthorizationHeader(authorizationHeader);
                String username = authService.findUsernameByToken(token);

                if (!Objects.isNull(username) &&
                        ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                                || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
                ) {
                    DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrderById(id);

                    if (deliveryOrder != null) {
                        return new ResponseEntity<>(deliveryOrder, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Delivery Order not found", HttpStatus.NOT_FOUND);
                    }
                } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // FOR CUSTOMER USE
    @PostMapping("/placeOrder/{customerId}")
    public ResponseEntity<?> setOrder(@PathVariable int customerId, @RequestBody DeliveryOrderDTO deliveryOrderDTO,@RequestHeader("Authorization") String authorizationHeader) {
        try {

            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);
            System.out.println(token+ username);
            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            )
            ) {
                DeliveryOrder newDeliveryOrder = deliveryOrderService.setOrder(customerId, deliveryOrderDTO);
                if (!Objects.isNull(newDeliveryOrder)) {
                    return new ResponseEntity<>("ORDER HAS BEEN PLACED SUCESSFULLY", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Failed to FULFILL the Order", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Access denied. Please login as Admin.", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/placedOrderDetails/{customerId}")
    public ResponseEntity<?> orderDetails(@PathVariable int customerId,@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name())
                            )
            ) {

                List<DeliveryOrder> newDeliveryOrder = deliveryOrderService.getDeliveryOrderByCustomerId(customerId);

                if (!newDeliveryOrder.isEmpty()) { // Corrected from !newDeliveryOrder.isEmpty()
                    return new ResponseEntity<>(newDeliveryOrder, HttpStatus.OK); // Return the actual object inside Optional
                } else {
                    return new ResponseEntity<>("YOU DIDN'T PLACED ANY ORDER", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDeliveryOrdersByGodownId/{godownId}")
    public ResponseEntity<?> deliveryOrderDetails(@PathVariable int godownId, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                List<DeliveryOrder> newDeliveryOrder = deliveryOrderService.getDeliveryOrderByGodownId(godownId);

                if (!newDeliveryOrder.isEmpty()) { // Corrected from !newDeliveryOrder.isEmpty()
                    return new ResponseEntity<>(newDeliveryOrder, HttpStatus.OK); // Return the actual object inside Optional
                } else {
                    return new ResponseEntity<>("YOU DIDN'T HAVE ANY DELIVERY ORDER", HttpStatus.BAD_REQUEST);
                }

            }
            else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTotalDeliveryProducts")
    public ResponseEntity<?> getTotalDeliveryProducts(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                return deliveryOrderService.getTotalDeliveryProducts();
            }
            else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    @GetMapping("/getTotalSalesCount/{godownId}")
    public ResponseEntity<?> deliveryOrderCountByGodownId(@PathVariable String godownId, @RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                int parsedGodownId = Integer.parseInt(godownId);
                return deliveryOrderService.DeliveryOrderCountByGodownId(parsedGodownId);

            }
            else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getSalesByDate")
    public ResponseEntity<?> getSalesOnDateByGodownId(@RequestParam("godownId") int godownId, @RequestParam("date") String date, @RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                Date parsedDate;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                parsedDate = dateFormat.parse(date);
                return deliveryOrderService.getTotalSalesOrdersByGodownIDAndDate(godownId, parsedDate);
            }
            else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getSalesByWeek")
    public ResponseEntity<?> getSalesByWeek(@RequestParam("godownId") int godownId,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                List<DeliveryOrder> orders = deliveryOrderService.getOrdersForYear(godownId, Calendar.getInstance().get(Calendar.YEAR));
//                System.out.println(orders);

                // Group orders by week
                List<Map<String, Object>> salesByWeek = groupOrdersByWeek(orders);

                return new ResponseEntity<>(salesByWeek, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }

    private List<Map<String, Object>> groupOrdersByWeek(List<DeliveryOrder> orders) {
        List<Map<String, Object>> salesByWeekList = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            Map<String, Object> weekData = new LinkedHashMap<>();
            weekData.put("week", "Week " + i);
            weekData.put("salesCount", 0L);
            salesByWeekList.add(weekData);
        }

        for (DeliveryOrder order : orders) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getOrderDate());
            int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            Map<String, Object> weekData = salesByWeekList.get(weekNumber - 1);
            weekData.put("salesCount", (Long) weekData.get("salesCount") + 1);
        }

        return salesByWeekList;
    }



    @GetMapping("/getOrderQuantityByWeek")
    public ResponseEntity<?> getOrderQuantityByWeek(@RequestParam("godownId") int godownId,
                                                    @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))) {
                List<DeliveryOrder> orders = deliveryOrderService.getProductsForYear(godownId, Calendar.getInstance().get(Calendar.YEAR));

                // Group orders by week
                List<Map<String, Object>> orderQuantityByWeek = groupProductsByWeek(orders);

                return new ResponseEntity<>(orderQuantityByWeek, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }

    private List<Map<String, Object>> groupProductsByWeek(List<DeliveryOrder> orders) {
        List<Map<String, Object>> orderQuantityByWeekList = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            Map<String, Object> weekData = new LinkedHashMap<>();
            weekData.put("week", "Week " + i);
            weekData.put("orderQuantity", 0); // Initialize order quantity to 0
            orderQuantityByWeekList.add(weekData);
        }

        for (DeliveryOrder order : orders) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getOrderDate());
            int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            Map<String, Object> weekData = orderQuantityByWeekList.get(weekNumber - 1);
            int orderQuantity = (int) weekData.get("orderQuantity");
            orderQuantity += order.getOrderQuantity(); // Add order quantity to existing quantity
            weekData.put("orderQuantity", orderQuantity);
        }

        return orderQuantityByWeekList;
    }






    @GetMapping("/getTopSellingProducts/{godownId}")
    public ResponseEntity<?> getTopSellingProductsByGodownId(@PathVariable String godownId, @RequestHeader("Authorization") String authorizationHeader){
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            String username = authService.findUsernameByToken(token);

            if (!Objects.isNull(username) &&
                    ("admin".equals(godownHeadService.getRoleByUsername(username).name())
                            || "godownhead".equals(godownHeadService.getRoleByUsername(username).name()))
            ) {
                int parsedGodownId = Integer.parseInt(godownId);
                return deliveryOrderService.getTopSellingProducts(parsedGodownId);
            }
            else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }

    }
}