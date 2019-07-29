//package com.yuntai.upp.access.restapi.fresh;
//
//import com.alibaba.fastjson.JSON;
//import com.yuntai.hdp.access.RequestPack;
//import com.yuntai.hdp.access.ResultKind;
//import com.yuntai.hdp.access.ResultPack;
//import com.yuntai.hdp.client.HdpClient;
//import com.yuntai.hdp.client.SynAccessHospital;
//import com.yuntai.upp.client.basic.enums.inner.CmdType;
//import com.yuntai.upp.client.basic.util.KeyUtil;
//import com.yuntai.upp.client.basic.util.LoggerUtil;
//import com.yuntai.upp.client.config.hdp.HdpClientProperties;
//import com.yuntai.upp.client.config.keypair.KeypairProperties;
//import com.yuntai.upp.sdk.enums.SignType;
//import com.yuntai.upp.sdk.interfaces.Signable;
//import com.yuntai.upp.sdk.util.MapUtil;
//import com.yuntai.upp.sdk.util.SignUtil;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @description HDP 客户端工具模拟
// * @className AbstractHdpClientTest
// * @package com.yuntai.upp.access.restapi.fresh
// * @author jinren@hsyuntai.com
// * @date 2019-07-29 10:29
// * @copyright 版权归 HSYUNTAI 所有
// */
//@Slf4j
//public class AbstractHdpClientTest {
//    private static final String HDP_PREFIX = "upp_";
//
//    private static HdpClient CLIENT = null;
//
//    /**
//     * @description 供工程启动主动申明
//     * @param property hdp 配置
//     * @return com.yuntai.hdp.client.HdpClient
//     * @author jinren@hsyuntai.com
//     * @date 2019-06-12 19:16
//     */
//    protected static synchronized HdpClient connect(@NonNull HdpClientProperties property, @NonNull SynAccessHospital bean) {
//        if (Objects.equals(CLIENT, null)) {
//            init(property, bean);
//        }
//        return CLIENT;
//    }
//
//    public static synchronized void close() {
//        if (Objects.equals(CLIENT, null)) {
//            return;
//        }
//        try {
//            CLIENT.close();
//        } catch (InterruptedException exception) {
//            LoggerUtil.error(LOGGER, "HDP 客户端关闭发生中断异常异常", exception);
//        }
//    }
//
//    /**
//     * @description 初始化
//     * @param property hdp配置
//     * @param bean bean
//     * @return void
//     * @author jinren@hsyuntai.com
//     * @date 2019-06-12 19:18
//     */
//    private static void init(@NonNull HdpClientProperties property, @NonNull SynAccessHospital bean) {
//        LoggerUtil.info(LOGGER, "HDP 建立连接方法开始执行");
//        try {
//            AbstractHdpClientTest.CLIENT = new HdpClient()
//                    .remoteAddress(property.getIp(), property.getPort())
//                    .hosId(HDP_PREFIX + property.getResourceId())
//                    .reconnectDelay(property.getDelay())
//                    .accessToken(property.getToken())
//                    .ssl(true)
//                    .synAccessHospital(bean)
//                    .businessThreadPoolSize(property.getPool())
//                    .connect();
//            if (AbstractHdpClientTest.CLIENT.isActive()) {
//                LoggerUtil.info(LOGGER, "HDP 连接建立成功");
//            } else {
//                LoggerUtil.warn(LOGGER, "HDP 连接建立失败，请确认HDP配置是否正确");
//                System.exit(1);
//            }
//        } catch (Exception exception) {
//            LoggerUtil.error(exception, LOGGER, "HDP 建立连接异常");
//            System.exit(1);
//        }
//    }
//
//    /**
//     * @description 通过 HDP 上传报文
//     *              响应结果以为成功为准(正向逻辑)
//     * @param cmd cmd 枚举
//     * @param param 报文(推送给云端服务)
//	 * @param cls 响应 bean 类型
//     * @return T
//     * @author jinren@hsyuntai.com
//     * @date 2019-07-24 18:05
//     */
//    public static <T, E extends Signable> T send(@NonNull CmdType cmd, @NonNull E param, Class<T> cls) {
//        KeypairProperties.KeyPair keyPair = KeyUtil.keyPair(param.getIsvId(), param.getPartnerId());
//
//        RequestPack data = new RequestPack();
//        try {
//            String sign = SignUtil.signRSA(param, keyPair.getPrivateKey(), SignType.RSA2);
//            Map<String, Object> sender = MapUtil.beanToMap(param);
//            sender.put("sign", sign);
//
//            data.setBody(JSON.toJSONString(sender));
//            data.setCmd(cmd.getCode());
//
//        } catch (Exception exception) {
//            throw new RuntimeException("客户端数据加密异常, 异常: " + exception.getMessage());
//        }
//        try {
//            ResultPack pack = CLIENT.sendData(data, 30);
//            if (Objects.equals(null, pack)) {
//                throw new RuntimeException("对象[ResultPack]非法, ResultPack对象为空");
//            }
//            if (!ResultKind.OK.getKind().equals(pack.getKind())) {
//                throw new RuntimeException(pack.getMsg());
//            }
//            if (!SignUtil.verifyRSA(JSON.<Map<String, Object>>parseObject(pack.getBody(), Map.class), keyPair.getPublicKey(), SignType.RSA2)) {
//                throw new RuntimeException("HDP 交互响应报文验签失败");
//            }
//            return JSON.parseObject(pack.getBody(), cls);
//        } catch (Exception exception) {
//            throw new IllegalStateException("客户端初步推送|解析响应异常, 异常: " + exception.getMessage());
//        }
//    }
//}
