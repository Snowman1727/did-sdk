package com.teleinfo.didsdk.test;

import com.alibaba.fastjson.JSONObject;
import com.teleinfo.didsdk.DidSdkApplication;
import com.teleinfo.didsdk.proxy.DIDClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest(classes = DidSdkApplication.class)
public class DidDocumentDetailTest {

    @Autowired
    private DIDClient didClient;

    @Test
    public void test_didQuery() throws Exception {
        JSONObject json = didClient.resolveDID("did:bid:abcd:sfPzn3TCN47jqQ8wzksgGdkK62ceNRRJD84", BigInteger.valueOf(0L));
        System.out.println(json.toJSONString());
    }
}
