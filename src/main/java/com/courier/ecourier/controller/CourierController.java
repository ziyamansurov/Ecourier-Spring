package com.courier.ecourier.controller;

import com.courier.ecourier.dto.request.ReqCourier;
import com.courier.ecourier.dto.response.RespCourier;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @RequestMapping(value = "/GetCourierList",method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<RespCourier>> getCourierList(){

        return courierService.getCourierList();
    }

//    @RequestMapping(value = "/GetCourierById",method = RequestMethod.GET)
//    public Response<RespCourier> getCourierById(@RequestParam("courid") Long courierid){
//
//      return   courierService.getCourierById(courierid);
//
//    }
@RequestMapping(value = "/GetCourierById/{id}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public Response<RespCourier> getCourierById(@PathVariable("id") Long courierid){

    return   courierService.getCourierById(courierid);

}

    @RequestMapping(value ="/AddCourier",method = RequestMethod.POST)
    public Response addCourier(@RequestBody ReqCourier reqCourier) {
       return courierService.addCourier(reqCourier);

           }
    @PutMapping(value = "/UpdateCourier",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response updateCourier(@RequestBody ReqCourier reqCourier){
        return courierService.updateCourier(reqCourier);
    }


//    @PostMapping("/AddCourier")
//    public ReqCourier addCourier(@RequestBody ReqCourier reqCourier){
//        return reqCourier;
//    }
//

}


