package com.yuntai.upp.access.tunnel;

import com.yuntai.upp.client.fresh.model.bo.Outcome;
import com.yuntai.upp.client.fresh.model.dto.channelbills.ChannelBillsDto;
import com.yuntai.upp.client.fresh.model.dto.yuntaibills.YuntaiBillsDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import static com.yuntai.upp.client.config.constant.ConstantInstance.CHANNEL_BILLS;
import static com.yuntai.upp.client.config.constant.ConstantInstance.YUNTAI_BILLS;
import static com.yuntai.upp.client.config.constant.ConstantInstance.REQUEST;
import static com.yuntai.upp.client.config.constant.ConstantInstance.RESPONSE;

/**
 * @description ws endpoint 控制
 * @className DetailTunnel
 * @package com.yuntai.upp.access.tunnel
 * @author jinren@hsyuntai.com
 * @date 2019/10/21 18:45
 * @copyright 版权归 HSYUNTAI 所有
 */

@WebService(targetNamespace = "http://service.webservice.openservice.yuntai.com/")
public interface DetailTunnel {

    /**
     * @WebService中的targetNamespace
     * @WebMethod中的operationName
     * @WebResult中的name
     * @WebParam中的name
     * 以上参数 请按照各自接入医院的老接口报文格式执行兼容补充
     * 请将模型创建于(com.yuntai.upp.access.model包下)
     * (若为新医院或未集成过老接口, 则请默认使用 demo 中的代码)
     */
    @WebMethod(operationName = YUNTAI_BILLS)
    @WebResult(name = RESPONSE)
    Outcome yuntaiBills(@WebParam(name = REQUEST) YuntaiBillsDto dto);

    @WebMethod(operationName = CHANNEL_BILLS)
    @WebResult(name = RESPONSE)
    Outcome channelBills(@WebParam(name = REQUEST) ChannelBillsDto dto);
}
