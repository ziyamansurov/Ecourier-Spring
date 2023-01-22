package com.courier.ecourier.service.impl;

import com.courier.ecourier.dto.request.ReqCourier;
import com.courier.ecourier.dto.response.RespCourier;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.Courier;
import com.courier.ecourier.enums.EnumDataStatus;
import com.courier.ecourier.exception.ECourierException;
import com.courier.ecourier.exception.ExceptionConstants;
import com.courier.ecourier.repository.CourierRepository;
import com.courier.ecourier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd");



    @Override
    public Response<List<RespCourier>> getCourierList() {




        Response<List<RespCourier>> response= new Response<List<RespCourier>>();
        try{


        List<RespCourier> respCouriers=courierRepository.findAllByActive(EnumDataStatus.ACTIVE.getValue()).stream().map(courier->{
            String birthday=null;
            if(courier.getBirthday()!=null){
                birthday=df.format(courier.getBirthday());

            }

            RespCourier respCourier=RespCourier.builder()
                    .id(courier.getId())
                    .name(courier.getName())
                    .surname(courier.getSurname())
                    .address(courier.getAddress())

                    .birthday(birthday)

                    .email(courier.getEmail())
                    .phonenumb(courier.getPhonenumb())
                    .image(courier.getImage())
                    .rating(courier.getRating())
                    .build();

            return respCourier;


        }).collect(Collectors.toList());

        if(respCouriers.isEmpty()){
            throw new ECourierException(ExceptionConstants.COURIER_NOT_FOUND,"Couirer not found");
        }
        response.setT(respCouriers);
        response.setStatus(RespStatus.getSuccessMessage());

        }
        catch (ECourierException ex){
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));


        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;



    }

    @Override
    public Response<RespCourier> getCourierById(Long courierid) {
        Response<RespCourier> response= new Response<RespCourier>();
        try{
            if(courierid==null){
                throw new ECourierException(ExceptionConstants.COURIER_NOT_FOUND,"Couirer not found");
            }

            Courier courier=courierRepository.findCourierByIdAndActive(courierid,EnumDataStatus.ACTIVE.getValue());
            String birthday=null;
            if(courier.getBirthday()!=null){
                birthday=df.format(courier.getBirthday());

            }
            RespCourier respCouriers= RespCourier.builder()
                    .id(courier.getId())
                    .name(courier.getName())
                    .surname(courier.getSurname())
                    .address(courier.getAddress())
                    .email(courier.getEmail())
                    .birthday(birthday)
                    .phonenumb(courier.getPhonenumb())
                    .rating(courier.getRating())
                    .image(courier.getImage())


                    .build();

            response.setT(respCouriers);
            response.setStatus(RespStatus.getSuccessMessage());

        }
        catch (ECourierException ex){
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));


        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;




    }

    @Override
    public Response addCourier(ReqCourier reqCourier) {
        Response response=new Response();

        try{

            String name=reqCourier.getName();
        String surname=reqCourier.getSurname();
         String address=reqCourier.getAddress();

         byte[] image=reqCourier.getImage();

         String birthday=reqCourier.getBirthday();

         int rating=reqCourier.getRating();

         String phonenumb=reqCourier.getPhonenumb();

         String email=reqCourier.getEmail();

         if(name==null||surname==null||address==null){
             throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
         }

             Courier courier=new Courier();
             courier.setName(name);
             courier.setSurname(surname);
             courier.setAddress(address);
             if(birthday!=null) courier.setBirthday(df.parse(birthday));

             courier.setPhonenumb(phonenumb);
             courier.setEmail(email);
             courier.setRating(rating);
             courier.setImage(image);

             courierRepository.save(courier);
             response.setStatus(RespStatus.getSuccessMessage());





         }
         catch (ECourierException ex){
             response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));

         }
        catch(Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }



        return response;
    }

    @Override
    public Response updateCourier(ReqCourier reqCourier) {
        Response response=new Response();
        try{
            Long id=reqCourier.getId();
            String name=reqCourier.getName();
            String surname=reqCourier.getSurname();
            String address=reqCourier.getAddress();
            String email=reqCourier.getEmail();
            String dob=reqCourier.getBirthday();
            Integer rating=reqCourier.getRating();
            String number=reqCourier.getPhonenumb();

            if(name==null ||
                    surname==null||
                    address==null||
                    id==null){
                throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
            }
            Courier courier=courierRepository.findCourierByIdAndActive(id,EnumDataStatus.ACTIVE.getValue());
            if(courier==null){
                throw new ECourierException(ExceptionConstants.COURIER_NOT_FOUND,"Courier Not Found!!!");
            }
            courier.setName(name);
            courier.setSurname(surname);
            courier.setAddress(address);
            courier.setEmail(email);
            courier.setBirthday(df.parse(dob));
            courier.setRating(rating);
            courier.setPhonenumb(number);
            courierRepository.save(courier);
            response.setT(courier);
            response.setStatus(RespStatus.getSuccessMessage());
        }
        catch (ECourierException ex){
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));
        }
        catch (Exception ex){

            ex.printStackTrace();
        }
        return response;
    }
}

//courierRepository.findAllByActive(1).stream().map(courier->{
//        RespCourier respCourier=new RespCourier();
//        respCourier.setId(courier.getId());
//        respCourier.setName(courier.getName());
//        respCourier.setSurname(courier.getSurname());
//        respCourier.setAddress(courier.getAddress());
//        respCourier.setEmail(courier.getEmail());
//        respCourier.setPhonenumb(courier.getPhonenumb());
//        respCourier.setImage(courier.getImage());
//        respCourier.setRating(courier.getRating());
//        return respCourier;
//
//
//        }).collect(Collectors.toList());

