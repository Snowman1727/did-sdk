package com.teleinfo.didsdk.bo.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.bifj.abi.EventEncoder;
import org.bifj.abi.TypeReference;
import org.bifj.abi.datatypes.Address;
import org.bifj.abi.datatypes.Bool;
import org.bifj.abi.datatypes.DynamicArray;
import org.bifj.abi.datatypes.DynamicBytes;
import org.bifj.abi.datatypes.Event;
import org.bifj.abi.datatypes.Type;
import org.bifj.abi.datatypes.generated.Uint256;
import org.bifj.protocol.Bifj;
import org.bifj.protocol.core.DefaultBlockParameter;
import org.bifj.protocol.core.RemoteCall;
import org.bifj.protocol.core.RemoteFunctionCall;
import org.bifj.protocol.core.methods.request.CoreFilter;
import org.bifj.protocol.core.methods.response.BaseEventResponse;
import org.bifj.protocol.core.methods.response.Log;
import org.bifj.protocol.core.methods.response.TransactionReceipt;
import org.bifj.tx.Contract;
import org.bifj.tx.TransactionManager;
import org.bifj.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.bifj.io/command_line.html">bifj command line tools</a>,
 * or the org.bifj.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/bifj/bifj/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with bifj version none.
 */
public class DIDRegistry extends Contract {
    private static final String BINARY = "60806040526000805534801561001457600080fd5b50611598806100246000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80635915b6981161005b5780635915b698146101195780637f6321161461012c578063daf68c6c1461016a578063e27f83821461017d57600080fd5b80632d3e3fbc1461008d57806331ef8871146100de57806332f3541d146100f357806351c7d61714610106575b600080fd5b6100c161009b366004610d3f565b80516020818301810180516001825292820191909301209152546001600160c01b031681565b6040516001600160c01b0390911681526020015b60405180910390f35b6100f16100ec366004610e53565b61019d565b005b6100f1610101366004610ea6565b610319565b6100f1610114366004610f29565b6104eb565b6100f1610127366004610f6a565b6105c9565b61015a61013a366004610d3f565b805160208183018101805160028252928201919093012091525460ff1681565b60405190151581526020016100d5565b6100f1610178366004610fe7565b6107e4565b61019061018b366004611052565b61092a565b6040516100d5919061109d565b6001600160c01b0381166101cc5760405162461bcd60e51b81526004016101c390611138565b60405180910390fd5b60006001600160c01b0316600184846040516101e9929190611161565b908152604051908190036020019020546001600160c01b03161461021f5760405162461bcd60e51b81526004016101c390611171565b6040805180820182526001600160c01b03831681528151600080825260208281019094529282019083610262565b606081526020019060019003908161024d5790505b508152509050806001858560405161027b929190611161565b90815260405160209181900382019020825181546001600160c01b0319166001600160c01b0390911617815582820151805191926102c192600185019290910190610c64565b5050600080549150806102d383611199565b91905055507fd082c713b72a5623ae6ae8b4d1d72d0c3c5c61a79b450fce1086fd9b470e0a1784848460405161030b939291906111e9565b60405180910390a150505050565b6001600160c01b03831661033f5760405162461bcd60e51b81526004016101c390611138565b60006001600160c01b03166001868660405161035c929190611161565b908152604051908190036020019020546001600160c01b0316146103925760405162461bcd60e51b81526004016101c390611171565b604080516001808252818301909252600091816020015b60608152602001906001900390816103a957905050905082828080601f016020809104026020016040519081016040528093929190818152602001838380828437600092018290525085518694509092501515905061040a5761040a611215565b602002602001018190525060006040518060400160405280866001600160c01b03168152602001838152509050806001888860405161044a929190611161565b90815260405160209181900382019020825181546001600160c01b0319166001600160c01b03909116178155828201518051919261049092600185019290910190610c64565b5050600080549150806104a283611199565b91905055507fd082c713b72a5623ae6ae8b4d1d72d0c3c5c61a79b450fce1086fd9b470e0a178787876040516104da939291906111e9565b60405180910390a150505050505050565b336001600160c01b031660018383604051610507929190611161565b908152604051908190036020019020546001600160c01b0316146105595760405162461bcd60e51b81526020600482015260096024820152683737ba2fb7bbb732b960b91b60448201526064016101c3565b60016002838360405161056d929190611161565b908152604051908190036020018120805492151560ff19909316929092179091557fb8589940edf244874b4515fe6123d586c1b13fa604f997356f0cff5b82cecebb906105bd908490849061122b565b60405180910390a15050565b6001600160c01b0383166105ef5760405162461bcd60e51b81526004016101c390611138565b604080516001808252818301909252600091816020015b606081526020019060019003908161060657905050905082828080601f016020809104026020016040519081016040528093929190818152602001838380828437600092018290525085518694509092501515905061066757610667611215565b602002602001018190525060006040518060400160405280866001600160c01b0316815260200183815250905060005b868110156107b057600060018989848181106106b5576106b5611215565b90506020028101906106c79190611247565b6040516106d5929190611161565b908152604051908190036020019020546001600160c01b03161461070b5760405162461bcd60e51b81526004016101c390611171565b81600189898481811061072057610720611215565b90506020028101906107329190611247565b604051610740929190611161565b90815260405160209181900382019020825181546001600160c01b0319166001600160c01b03909116178155828201518051919261078692600185019290910190610c64565b50506000805491508061079883611199565b919050555080806107a890611199565b915050610697565b507f6f37ac5d7fec8ea7d4565da147e9d0ba59a70c0e894579abd3907fb03be752558787876040516104da9392919061128d565b600284846040516107f6929190611161565b9081526040519081900360200190205460ff16156108445760405162461bcd60e51b815260206004820152600b60248201526a19195858dd1a5d985d195960aa1b60448201526064016101c3565b336001600160c01b031660018585604051610860929190611161565b908152604051908190036020019020546001600160c01b0316146108b25760405162461bcd60e51b81526020600482015260096024820152683737ba2fb7bbb732b960b91b60448201526064016101c3565b600184846040516108c4929190611161565b90815260405160209181900382019020600190810180549182018155600090815291909120016108f58284836113bf565b506040517f371aba6d38dcdf7b2457a88a141e830614a3ff7f2fe63904faf59223d7834f939061030b9086908690859061147f565b606060006001600160c01b031660018585604051610949929190611161565b908152604051908190036020019020546001600160c01b03160361099b5760405162461bcd60e51b81526020600482015260096024820152681b9bdd17d95e1a5cdd60ba1b60448201526064016101c3565b600184846040516109ad929190611161565b90815260405190819003602001902060010154821115610a035760405162461bcd60e51b81526020600482015260116024820152701d995c9cda5bdb97db9bdd17d95e1a5cdd607a1b60448201526064016101c3565b8115610b63576000826001600160401b03811115610a2357610a23610d29565b604051908082528060200260200182016040528015610a5657816020015b6060815260200190600190039081610a415790505b50905060005b83811015610b5b5760018686604051610a76929190611161565b90815260200160405180910390206001018181548110610a9857610a98611215565b906000526020600020018054610aad90611336565b80601f0160208091040260200160405190810160405280929190818152602001828054610ad990611336565b8015610b265780601f10610afb57610100808354040283529160200191610b26565b820191906000526020600020905b815481529060010190602001808311610b0957829003601f168201915b5050505050828281518110610b3d57610b3d611215565b60200260200101819052508080610b5390611199565b915050610a5c565b509050610c5d565b600060018585604051610b77929190611161565b9081526020016040518091039020600101805480602002602001604051908101604052809291908181526020016000905b82821015610c54578382906000526020600020018054610bc790611336565b80601f0160208091040260200160405190810160405280929190818152602001828054610bf390611336565b8015610c405780601f10610c1557610100808354040283529160200191610c40565b820191906000526020600020905b815481529060010190602001808311610c2357829003601f168201915b505050505081526020019060010190610ba8565b50929450505050505b9392505050565b828054828255906000526020600020908101928215610caa579160200282015b82811115610caa5782518290610c9a90826114a3565b5091602001919060010190610c84565b50610cb6929150610cba565b5090565b80821115610cb6576000610cce8282610cd7565b50600101610cba565b508054610ce390611336565b6000825580601f10610cf3575050565b601f016020900490600052602060002090810190610d119190610d14565b50565b5b80821115610cb65760008155600101610d15565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610d5157600080fd5b81356001600160401b0380821115610d6857600080fd5b818401915084601f830112610d7c57600080fd5b813581811115610d8e57610d8e610d29565b604051601f8201601f19908116603f01168101908382118183101715610db657610db6610d29565b81604052828152876020848701011115610dcf57600080fd5b826020860160208301376000928101602001929092525095945050505050565b60008083601f840112610e0157600080fd5b5081356001600160401b03811115610e1857600080fd5b602083019150836020828501011115610e3057600080fd5b9250929050565b80356001600160c01b0381168114610e4e57600080fd5b919050565b600080600060408486031215610e6857600080fd5b83356001600160401b03811115610e7e57600080fd5b610e8a86828701610def565b9094509250610e9d905060208501610e37565b90509250925092565b600080600080600060608688031215610ebe57600080fd5b85356001600160401b0380821115610ed557600080fd5b610ee189838a01610def565b9097509550859150610ef560208901610e37565b94506040880135915080821115610f0b57600080fd5b50610f1888828901610def565b969995985093965092949392505050565b60008060208385031215610f3c57600080fd5b82356001600160401b03811115610f5257600080fd5b610f5e85828601610def565b90969095509350505050565b600080600080600060608688031215610f8257600080fd5b85356001600160401b0380821115610f9957600080fd5b818801915088601f830112610fad57600080fd5b813581811115610fbc57600080fd5b8960208260051b8501011115610fd157600080fd5b60208301975080965050610ef560208901610e37565b60008060008060408587031215610ffd57600080fd5b84356001600160401b038082111561101457600080fd5b61102088838901610def565b9096509450602087013591508082111561103957600080fd5b5061104687828801610def565b95989497509550505050565b60008060006040848603121561106757600080fd5b83356001600160401b0381111561107d57600080fd5b61108986828701610def565b909790965060209590950135949350505050565b6000602080830181845280855180835260408601915060408160051b87010192508387016000805b8381101561112a57888603603f1901855282518051808852835b818110156110fa578281018a01518982018b015289016110df565b8181111561110a57848a838b0101525b50601f01601f1916969096018701955093860193918601916001016110c5565b509398975050505050505050565b6020808252600f908201526e3d32b937afb7bbb732b92fb0b2323960891b604082015260600190565b8183823760009101908152919050565b6020808252600e908201526d616c72656164795f65786973747360901b604082015260600190565b6000600182016111b957634e487b7160e01b600052601160045260246000fd5b5060010190565b81835281816020850137506000828201602090810191909152601f909101601f19169091010190565b6040815260006111fd6040830185876111c0565b905060018060c01b0383166020830152949350505050565b634e487b7160e01b600052603260045260246000fd5b60208152600061123f6020830184866111c0565b949350505050565b6000808335601e1984360301811261125e57600080fd5b8301803591506001600160401b0382111561127857600080fd5b602001915036819003821315610e3057600080fd5b6040808252810183905260006060600585901b8301810190830186835b8781101561131d57858403605f190183528135368a9003601e190181126112d057600080fd5b890160208181019135906001600160401b038211156112ee57600080fd5b8136038313156112fd57600080fd5b6113088783856111c0565b965094850194939093019250506001016112aa565b5050506001600160c01b0384166020840152905061123f565b600181811c9082168061134a57607f821691505b60208210810361136a57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156113ba57600081815260208120601f850160051c810160208610156113975750805b601f850160051c820191505b818110156113b6578281556001016113a3565b5050505b505050565b6001600160401b038311156113d6576113d6610d29565b6113ea836113e48354611336565b83611370565b6000601f84116001811461141e57600085156114065750838201355b600019600387901b1c1916600186901b178355611478565b600083815260209020601f19861690835b8281101561144f578685013582556020948501946001909201910161142f565b508682101561146c5760001960f88860031b161c19848701351681555b505060018560011b0183555b5050505050565b6040815260006114936040830185876111c0565b9050826020830152949350505050565b81516001600160401b038111156114bc576114bc610d29565b6114d0816114ca8454611336565b84611370565b602080601f83116001811461150557600084156114ed5750858301515b600019600386901b1c1916600185901b1785556113b6565b600085815260208120601f198616915b8281101561153457888601518255948401946001909101908401611515565b50858210156115525787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea26469706673582212201eb73157ce83a0a94259acfd264d897ff08ed6e776f7a88e1e83153ac921e06464736f6c63430008100033";

    public static final String FUNC_CREATEBATCHDIDSWITHOPTION = "createBatchDIDsWithOption";

    public static final String FUNC_CREATEDID = "createDID";

    public static final String FUNC_CREATEDIDWITHOPTION = "createDIDWithOption";

    public static final String FUNC_DEACTIVATEDID = "deactivateDID";

    public static final String FUNC_DEACTIVATED = "deactivated";

    public static final String FUNC_DIDDOCUMENTS = "didDocuments";

    public static final String FUNC_RESOLVEDID = "resolveDID";

    public static final String FUNC_UPDATEDID = "updateDID";

    public static final Event DIDBATCHCREATION_EVENT = new Event("DIDBatchCreation",
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicBytes>>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event DIDCREATION_EVENT = new Event("DIDCreation",
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event DIDDEACTIVATION_EVENT = new Event("DIDDeactivation",
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event DIDUPATE_EVENT = new Event("DIDUpate",
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}));
    ;

    protected DIDRegistry(String contractAddress, Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, bifj, transactionManager, contractGasProvider);
    }

    public List<DIDBatchCreationEventResponse> getDIDBatchCreationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DIDBATCHCREATION_EVENT, transactionReceipt);
        ArrayList<DIDBatchCreationEventResponse> responses = new ArrayList<DIDBatchCreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DIDBatchCreationEventResponse typedResponse = new DIDBatchCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dids = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.didOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DIDBatchCreationEventResponse> dIDBatchCreationEventFlowable(CoreFilter filter) {
        return bifj.coreLogFlowable(filter).map(new Function<Log, DIDBatchCreationEventResponse>() {
            @Override
            public DIDBatchCreationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DIDBATCHCREATION_EVENT, log);
                DIDBatchCreationEventResponse typedResponse = new DIDBatchCreationEventResponse();
                typedResponse.log = log;
                typedResponse.dids = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.didOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DIDBatchCreationEventResponse> dIDBatchCreationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        CoreFilter filter = new CoreFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DIDBATCHCREATION_EVENT));
        return dIDBatchCreationEventFlowable(filter);
    }

    public List<DIDCreationEventResponse> getDIDCreationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DIDCREATION_EVENT, transactionReceipt);
        ArrayList<DIDCreationEventResponse> responses = new ArrayList<DIDCreationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DIDCreationEventResponse typedResponse = new DIDCreationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.didOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DIDCreationEventResponse> dIDCreationEventFlowable(CoreFilter filter) {
        return bifj.coreLogFlowable(filter).map(new Function<Log, DIDCreationEventResponse>() {
            @Override
            public DIDCreationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DIDCREATION_EVENT, log);
                DIDCreationEventResponse typedResponse = new DIDCreationEventResponse();
                typedResponse.log = log;
                typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.didOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DIDCreationEventResponse> dIDCreationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        CoreFilter filter = new CoreFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DIDCREATION_EVENT));
        return dIDCreationEventFlowable(filter);
    }

    public List<DIDDeactivationEventResponse> getDIDDeactivationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DIDDEACTIVATION_EVENT, transactionReceipt);
        ArrayList<DIDDeactivationEventResponse> responses = new ArrayList<DIDDeactivationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DIDDeactivationEventResponse typedResponse = new DIDDeactivationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DIDDeactivationEventResponse> dIDDeactivationEventFlowable(CoreFilter filter) {
        return bifj.coreLogFlowable(filter).map(new Function<Log, DIDDeactivationEventResponse>() {
            @Override
            public DIDDeactivationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DIDDEACTIVATION_EVENT, log);
                DIDDeactivationEventResponse typedResponse = new DIDDeactivationEventResponse();
                typedResponse.log = log;
                typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DIDDeactivationEventResponse> dIDDeactivationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        CoreFilter filter = new CoreFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DIDDEACTIVATION_EVENT));
        return dIDDeactivationEventFlowable(filter);
    }

    public List<DIDUpateEventResponse> getDIDUpateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DIDUPATE_EVENT, transactionReceipt);
        ArrayList<DIDUpateEventResponse> responses = new ArrayList<DIDUpateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DIDUpateEventResponse typedResponse = new DIDUpateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.versionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DIDUpateEventResponse> dIDUpateEventFlowable(CoreFilter filter) {
        return bifj.coreLogFlowable(filter).map(new Function<Log, DIDUpateEventResponse>() {
            @Override
            public DIDUpateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DIDUPATE_EVENT, log);
                DIDUpateEventResponse typedResponse = new DIDUpateEventResponse();
                typedResponse.log = log;
                typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.versionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DIDUpateEventResponse> dIDUpateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        CoreFilter filter = new CoreFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DIDUPATE_EVENT));
        return dIDUpateEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createBatchDIDsWithOption(List<byte[]> dids, String didOwner, byte[] option) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_CREATEBATCHDIDSWITHOPTION,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicArray<org.bifj.abi.datatypes.DynamicBytes>(
                                org.bifj.abi.datatypes.DynamicBytes.class,
                                org.bifj.abi.Utils.typeMap(dids, org.bifj.abi.datatypes.DynamicBytes.class)),
                        new org.bifj.abi.datatypes.Address(didOwner),
                        new org.bifj.abi.datatypes.DynamicBytes(option)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createDID(byte[] did, String didOwner) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_CREATEDID,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(did),
                        new org.bifj.abi.datatypes.Address(didOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createDIDWithOption(byte[] did, String didOwner, byte[] option) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_CREATEDIDWITHOPTION,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(did),
                        new org.bifj.abi.datatypes.Address(didOwner),
                        new org.bifj.abi.datatypes.DynamicBytes(option)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deactivateDID(byte[] did) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_DEACTIVATEDID,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> deactivated(byte[] param0) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(FUNC_DEACTIVATED,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> didDocuments(byte[] param0) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(FUNC_DIDDOCUMENTS,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> resolveDID(byte[] did, BigInteger versionId) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(FUNC_RESOLVEDID,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(did),
                        new org.bifj.abi.datatypes.generated.Uint256(versionId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicBytes>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateDID(byte[] did, byte[] docPatch) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_UPDATEDID,
                Arrays.<Type>asList(new org.bifj.abi.datatypes.DynamicBytes(did),
                        new org.bifj.abi.datatypes.DynamicBytes(docPatch)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static DIDRegistry load(String contractAddress, Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DIDRegistry(contractAddress, bifj, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DIDRegistry> deploy(Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DIDRegistry.class, bifj, transactionManager, contractGasProvider, BINARY, "");
    }

    public static class DIDBatchCreationEventResponse extends BaseEventResponse {
        public List<byte[]> dids;

        public String didOwner;
    }

    public static class DIDCreationEventResponse extends BaseEventResponse {
        public byte[] did;

        public String didOwner;
    }

    public static class DIDDeactivationEventResponse extends BaseEventResponse {
        public byte[] did;
    }

    public static class DIDUpateEventResponse extends BaseEventResponse {
        public byte[] did;

        public BigInteger versionId;
    }
}
