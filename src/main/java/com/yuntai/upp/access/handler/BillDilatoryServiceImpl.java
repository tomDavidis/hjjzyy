package com.yuntai.upp.access.handler;

import com.yuntai.upp.client.handler.active.BillDilatoryInstance;
import com.yuntai.upp.model.dto.access.dilatory.DilatoryDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("billDilatoryService")
public class BillDilatoryServiceImpl extends BillDilatoryInstance {

    @Override
    @Async("executor")
    protected void done(DilatoryDto dto) {
    }
}
