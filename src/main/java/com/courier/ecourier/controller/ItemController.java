package com.courier.ecourier.controller;

import com.courier.ecourier.dto.request.ReqItem;
import com.courier.ecourier.dto.response.RespItem;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/ItemList")
    public Response<List<RespItem>> itemList(){

        return itemService.getItemList();
    }
    @PostMapping("/AddItem")
    public Response addItem(@RequestBody ReqItem item){

        return itemService.addItem(item);
    }
    @GetMapping("/FindItemById/{id}")
    public Response findItemById(@PathVariable("id") Long id){

        return itemService.findItemById(id);
    }
    @DeleteMapping("/DeleteItem/{id}")
    public Response deleteItem(@PathVariable("id") Long id){

      return   itemService.deleteItemById(id);

    }
}
