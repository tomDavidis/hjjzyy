//package com.yuntai.upp.access.ws;
//
//import com.yuntai.upp.model.ws.Sender;
//import com.yuntai.upp.model.ws.SenderContent;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class NetworkTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    public void testNetwork() {
//        Sender sender = new Sender();
//        SenderContent senderContent = new SenderContent();
//        senderContent.setCode("123123");
//        senderContent.setCell("1xzsdfa");
//        sender.setBody(senderContent);
//        webTestClient.post().uri("/hs-access-facepay/services/facePayWebService")
//                .contentType(MediaType.TEXT_XML)
//                .body(Mono.just(sender), Sender.class)
//                .accept(MediaType.APPLICATION_XML)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
//    }
//
//}
