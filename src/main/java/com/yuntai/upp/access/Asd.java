package com.yuntai.upp.access;

import com.alibaba.fastjson.JSON;
import com.yuntai.upp.client.fresh.model.dto.paymentcallback.PaymentCallbackDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Asd {

    private static final List<String> FILTER = new ArrayList<String>() {
        private static final long serialVersionUID = -1765718623072864806L;
        {
            add("ISV_ID");
            add("serialVersionUID");
        }
    };


    public static void main(String[] args) {
        Arrays.stream(PaymentCallbackDto.class.getDeclaredFields())
                .forEach(field -> {
                    Arrays.stream(field.getDeclaredAnnotations())
                            .forEach(annotation ->  {
                                System.out.println(field.getName() + " - " + JSON.toJSONString(annotation));
                            });

                });
    }
}
