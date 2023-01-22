package com.courier.ecourier.service.impl;

import com.courier.ecourier.dto.request.ReqItem;
import com.courier.ecourier.dto.response.RespItem;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.Item;
import com.courier.ecourier.entity.Item_options;
import com.courier.ecourier.enums.EnumDataStatus;
import com.courier.ecourier.exception.ECourierException;
import com.courier.ecourier.exception.ExceptionConstants;
import com.courier.ecourier.repository.ItemRepository;
import com.courier.ecourier.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Response<List<RespItem>> getItemList() {
        Response<List<RespItem>> response = new Response<>();
        try {
            List<RespItem> respItems = itemRepository.findItemByActive(EnumDataStatus.ACTIVE.getValue()).stream().map(item -> itemToRespItem(item)).collect(Collectors.toList());


            if (respItems.isEmpty()) {
                throw new ECourierException(ExceptionConstants.ITEMS_NOT_FOUND, "Items not found");
            }

            response.setT(respItems);
            response.setStatus(RespStatus.getSuccessMessage());
        }
        catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }

        catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;

    }

    @Override
    public Response addItem(ReqItem item) {
        Response response=new Response();
        try{

            String name= item.getName();
            String category=item.getCategory();
            Item_options itemOptions= item.getItemOptions();
            if(name==null||category==null||itemOptions==null){
                throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid Request Data");

            }
            Item newItem=new Item();
            newItem.setName(name);
            newItem.setCategory(category);
            newItem.setItemOptions(itemOptions);
            itemRepository.save(newItem);
            response.setStatus(RespStatus.getSuccessMessage());

        }
        catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findItemById(Long id) {
        Response response=new Response();
        try{
            if(id==null){
                throw new ECourierException(ExceptionConstants.ITEMS_NOT_FOUND,"Item can't be found by  this id");
            }
            Item item=itemRepository.findItemByIdAndActive(id,EnumDataStatus.ACTIVE.getValue());
            if(item==null){
                throw new ECourierException(ExceptionConstants.ITEMS_NOT_FOUND,"Item can't be found");
            }
            response.setT(item);
            response.setStatus(RespStatus.getSuccessMessage());


        }  catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response deleteItemById(Long id) {
        Response response=new Response();

        try{
            if(id==null){
                throw new ECourierException(ExceptionConstants.ITEMS_NOT_FOUND,"Item can't be found by  this id");
            }
            Item item=itemRepository.findItemByIdAndActive(id,EnumDataStatus.ACTIVE.getValue());
            if(item==null){
                throw new ECourierException(ExceptionConstants.ITEMS_NOT_FOUND,"Item can't be found");
            }
            item.setActive(EnumDataStatus.INACTIVE.getValue());
            itemRepository.save(item);

            response.setStatus(RespStatus.getSuccessMessage());


        }  catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespItem itemToRespItem(Item item){

        return RespItem.builder()
                .id(item.getId())
                .name(item.getName())
                .category(item.getCategory())
                .itemOptions(item.getItemOptions())
                .build();

    }

}
