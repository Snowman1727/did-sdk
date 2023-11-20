package com.teleinfo.didsdk.proxy;



import com.alibaba.fastjson.JSONObject;
import com.teleinfo.didsdk.bo.solidity.DIDRegistry;
import com.teleinfo.didsdk.util.JsonPatchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bifj.crypto.Credentials;
import org.bifj.protocol.core.DefaultBlockParameterName;
import org.bifj.protocol.core.RemoteFunctionCall;
import org.bifj.protocol.core.methods.request.Transaction;
import org.bifj.protocol.core.methods.response.CoreCall;
import org.bifj.protocol.core.methods.response.TransactionReceipt;
import org.bifj.protocol.gbif.Gbif;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DIDClient {

    private final DIDRegistry didRegistry;

    private final Credentials credentials;

    private final Gbif gbif;

    @Value("${chain.address.did}")
    private String didContractAddress;



    public boolean createDID(String did) {
        log.info("DIDClient create did start,did: {}",did);
        RemoteFunctionCall<TransactionReceipt> record = didRegistry.createDID(did.getBytes(), credentials.getAddress());
        try {
            CompletableFuture<TransactionReceipt> recordFuture = record.sendAsync();
            TransactionReceipt receipt = recordFuture.get();
            log.info("DIDClient create did success, did: {}", did);
            return receipt.isStatusOK();
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall ;
            try {
                coreCall = gbif.coreCall(
                        tx,
                        DefaultBlockParameterName.LATEST
                ).send();
            } catch (Exception ex) {
                log.error("DIDClient did creation system error",e);
                return false;
            }

            String revertReason = coreCall.getRevertReason();
            log.error("DIDClient did creation event log not found, maybe transaction not exec,did={},error={}",did,revertReason,e);


            return false;
        }
    }

    public boolean createDIDWithOption(String did, String option) {
        log.info("createDIDWithOption did,option,{},{}",did,option);
        RemoteFunctionCall<TransactionReceipt> record = didRegistry.createDIDWithOption(did.getBytes(),credentials.getAddress(),option.getBytes());

        try {
            CompletableFuture<TransactionReceipt> recordFuture = record.sendAsync();
            TransactionReceipt recordReceipt = recordFuture.get();
            log.info("DIDClient create did with option success, did: {}", did);
            return recordReceipt.isStatusOK();
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall ;
            try {
                coreCall = gbif.coreCall(
                        tx,
                        DefaultBlockParameterName.LATEST
                ).send();
            } catch (Exception ex) {
                log.error("DIDClient did creation with option system error",e);
                return false;
            }

            String revertReason = coreCall.getRevertReason();
            log.error("DIDClient did creation with option event log not found, maybe transaction not exec,did={},error={}",did,revertReason,e);
            return false;
        }

    }

    public boolean createBatchDIDsWithOption(List<String> dids, String option) {
        log.info("createDIDWithOption did,option,{},{}",dids,option);
        List<byte[]> didByteArrayList = dids.stream().map(s -> s.getBytes()).collect(Collectors.toList());

        RemoteFunctionCall<TransactionReceipt> record = didRegistry.createBatchDIDsWithOption(didByteArrayList,credentials.getAddress(),option.getBytes());

        try {
            CompletableFuture<TransactionReceipt> recordFuture = record.sendAsync();
            TransactionReceipt recordReceipt = recordFuture.get();
            log.info("DIDClient batch create did with option success, did: {}", dids);
            return recordReceipt.isStatusOK();
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall ;
            try {
                coreCall = gbif.coreCall(
                        tx,
                        DefaultBlockParameterName.LATEST
                ).send();
            } catch (Exception ex) {
                log.error("DIDClient batch did creation with option system error",e);
                return false;
            }

            String revertReason = coreCall.getRevertReason();
            log.error("DIDClient batch did creation with option event log not found, maybe transaction not exec,did={},error={}",dids,revertReason,e);
            return false;
        }

    }

    public JSONObject resolveDID(String did, BigInteger versionId) throws Exception {
        RemoteFunctionCall<List> record = didRegistry.resolveDID(did.getBytes(), versionId);
        List list;
        try {
            CompletableFuture<List> recordFuture = record.sendAsync();
            list = recordFuture.get();
            JSONObject json = new JSONObject();

            for(Object obj : list){
                byte[] b = (byte[])obj;
                String patch = new String(b);
                String s1 = JsonPatchUtils.applyPatch(json.toJSONString(),patch);
                json = JSONObject.parseObject(s1);

            }
            return json;
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall = gbif.coreCall(
                    tx,
                    DefaultBlockParameterName.LATEST
            ).send();

            String revertReason = coreCall.getRevertReason();
            log.error("DIDClient resolveDID event log not found, maybe transaction not exec,did={},error={}",did,revertReason,e);
            throw e;
        }

    }

    public boolean updateDID(String did, String option) {
        log.info("updateDID did,option,{},{}",did,option);

        RemoteFunctionCall<TransactionReceipt> record = didRegistry.updateDID(did.getBytes(),option.getBytes());

        try {
            CompletableFuture<TransactionReceipt> recordFuture = record.sendAsync();
            TransactionReceipt recordReceipt = recordFuture.get();
            return recordReceipt.isStatusOK();
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall ;
            try {
                coreCall = gbif.coreCall(
                        tx,
                        DefaultBlockParameterName.LATEST
                ).send();
            } catch (Exception ex) {
                log.error("DIDClient updateDID system error",e);
                return false;
            }

            String revertReason = coreCall.getRevertReason();
            log.error("DIDClient updateDID event log not found, maybe transaction not exec,did={},error={}",did,revertReason,e);
            return false;
        }

    }

    public boolean deactivateDID(String did) {
        log.info("deactivateDID did,{}",did);
        RemoteFunctionCall<TransactionReceipt> record = didRegistry.deactivateDID(did.getBytes());
        try {
            CompletableFuture<TransactionReceipt> recordFuture = record.sendAsync();
            TransactionReceipt recordReceipt = recordFuture.get();
            return recordReceipt.isStatusOK();
        } catch (Exception e) {
            Transaction tx = Transaction.createCoreCallTransaction(
                    credentials.getAddress(),
                    didContractAddress,
                    record.encodeFunctionCall()
            );
            CoreCall coreCall ;
            try {
                coreCall = gbif.coreCall(
                        tx,
                        DefaultBlockParameterName.LATEST
                ).send();
            } catch (Exception ex) {
                log.error("DIDClient deactivate system error",e);
                return false;
            }

            String revertReason = coreCall.getRevertReason();

            log.error("DIDClient deactivate event log not found, maybe transaction not exec,did={},error={}",did,revertReason,e);
            return false;
        }

    }
}
