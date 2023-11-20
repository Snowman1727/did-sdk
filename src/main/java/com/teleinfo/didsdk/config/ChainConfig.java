package com.teleinfo.didsdk.config;

import com.teleinfo.didsdk.bo.solidity.DIDRegistry;
import com.teleinfo.didsdk.bo.solidity.EvidenceRegistry;
import org.bifj.crypto.Credentials;
import org.bifj.protocol.gbif.Gbif;
import org.bifj.protocol.http.HttpService;
import org.bifj.tx.RawTransactionManager;
import org.bifj.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

@Configuration
public class ChainConfig {

    @Value("${chain.url}")
    private String chainUrl;

    @Value("${chain.code}")
    private String chainCode;

    @Value("${chain.privateKey}")
    private String privateKey;


    @Value("${chain.address.did}")
    private String didContractAddress;

    @Value("${chain.address.evidence}")
    private String evidenceContractAddress;


    @Bean
    public Gbif buildGbif(){
        return Gbif.build(new HttpService(chainUrl));
    }

    @Bean
    public Credentials create(){
        return Credentials.create(privateKey, 1, chainCode);
    }

    @Bean
    public DIDRegistry buildDid(Gbif gbif , Credentials credentials) throws Exception {
        // 节点信息

        // 固定配置
        BigInteger chainId = new BigInteger(gbif.coreChainId().send().getResult().substring(2), 16);
        BigInteger gasPrice = new BigInteger("0");
        BigInteger gasLimit = new BigInteger("8000000");

        // 调用
        return DIDRegistry.load(
                didContractAddress,
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        );
    }

    @Bean
    public EvidenceRegistry buildEvidence(Gbif gbif , Credentials credentials) throws Exception {
        // 节点信息

        // 固定配置
        BigInteger chainId = new BigInteger(gbif.coreChainId().send().getResult().substring(2), 16);
        BigInteger gasPrice = new BigInteger("0");
        BigInteger gasLimit = new BigInteger("8000000");

        // 调用
        return EvidenceRegistry.load(
                evidenceContractAddress,
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        );
    }

    public static void main(String[] args) throws Exception {
        Gbif gbif = Gbif.build(new HttpService("http://139.198.43.151:32384"));
        Credentials credentials = Credentials.create("56ada162e26680b6640b9368e29c794f0dddcfbaaa13867ece775db4c0f1f19e", 0, "abcd");

//        File file = new File("/Users/guitai/pass.txt");
//        Credentials c = WalletUtils.loadCredentials("teleinfo456",file);
//        String privateKey = c.getPrivateKey();
        // 钱包，参数：私钥，类型，chainCode
//        deployDid(gbif,credentials);
        deployEvidence(gbif,credentials);

    }

    private static void deployDid(Gbif gbif,Credentials credentials) throws Exception {
        BigInteger chainId = new BigInteger(gbif.coreChainId().send().getResult().substring(2), 16);
        BigInteger gasPrice = new BigInteger("0");
        BigInteger gasLimit = new BigInteger("8000000");
        // 部署 只能部署一次
        DIDRegistry contract = DIDRegistry.deploy(
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        ).send();

        String contractAddress = contract.getContractAddress();
        System.out.println(contractAddress);

        // 调用
        DIDRegistry contract2 = DIDRegistry.load(
                contractAddress,
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        );
    }

    private static void deployEvidence(Gbif gbif,Credentials credentials) throws Exception {
        BigInteger chainId = new BigInteger(gbif.coreChainId().send().getResult().substring(2), 16);
        BigInteger gasPrice = new BigInteger("0");
        BigInteger gasLimit = new BigInteger("8000000");

        EvidenceRegistry contract = EvidenceRegistry.deploy(
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        ).send();

        String contractAddress = contract.getContractAddress();
        System.out.println(contractAddress);

        // 调用
        EvidenceRegistry contract2 = EvidenceRegistry.load(
                contractAddress,
                gbif,
                new RawTransactionManager(gbif, credentials, chainId.longValue()),
                new StaticGasProvider(gasPrice, gasLimit)
        );
    }
}
