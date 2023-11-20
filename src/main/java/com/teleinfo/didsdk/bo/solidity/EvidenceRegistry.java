package com.teleinfo.didsdk.bo.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.bifj.abi.EventEncoder;
import org.bifj.abi.TypeReference;
import org.bifj.abi.datatypes.Address;
import org.bifj.abi.datatypes.DynamicBytes;
import org.bifj.abi.datatypes.Event;
import org.bifj.abi.datatypes.Type;
import org.bifj.abi.datatypes.generated.Bytes32;
import org.bifj.crypto.Credentials;
import org.bifj.protocol.Bifj;
import org.bifj.protocol.core.DefaultBlockParameter;
import org.bifj.protocol.core.RemoteCall;
import org.bifj.protocol.core.RemoteFunctionCall;
import org.bifj.protocol.core.methods.request.CoreFilter;
import org.bifj.protocol.core.methods.response.BaseEventResponse;
import org.bifj.protocol.core.methods.response.Log;
import org.bifj.protocol.core.methods.response.TransactionReceipt;
import org.bifj.tuples.generated.Tuple2;
import org.bifj.tx.Contract;
import org.bifj.tx.TransactionManager;
import org.bifj.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.bifj.io/command_line.html">bifj command line tools</a>,
 * or the org.bifj.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/bifj/bifj/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with bifj version none.
 */
public class EvidenceRegistry extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160c01b031916331790556106c3806100326000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063ddcfd89514610076578063eca8851114610097575b600080fd5b600054610059906001600160c01b031681565b6040516001600160c01b0390911681526020015b60405180910390f35b6100896100843660046103d6565b6100ac565b60405161006d929190610418565b6100aa6100a5366004610475565b6101ff565b005b600060606000801b600185856040516100c69291906104ef565b908152604051908190036020019020540361011d5760405162461bcd60e51b8152602060048201526012602482015271195d9a59195b98d9481b9bdd08195e1a5cdd60721b60448201526064015b60405180910390fd5b6000600185856040516101319291906104ef565b908152602001604051809103902060405180604001604052908160008201548152602001600182018054610164906104ff565b80601f0160208091040260200160405190810160405280929190818152602001828054610190906104ff565b80156101dd5780601f106101b2576101008083540402835291602001916101dd565b820191906000526020600020905b8154815290600101906020018083116101c057829003601f168201915b50505050508152505090508060000151816020015192509250505b9250929050565b6000546001600160c01b031633146102595760405162461bcd60e51b815260206004820181905260248201527f6d73672e73656e646572206973206e6f7420636f6e7472616374206f776e65726044820152606401610114565b6000801b6001868660405161026f9291906104ef565b90815260405190819003602001902054146102c55760405162461bcd60e51b8152602060048201526016602482015275195d9a59195b98d948185b1c9958591e48195e1a5cdd60521b6044820152606401610114565b6000604051806040016040528085815260200184848080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152505050915250604051909150819060019061032890899089906104ef565b908152604051602091819003820190208251815590820151600182019061034f908261059e565b509050507fe41800a83536ab7b820f3c42d700739938f705d2de100ceea03b78000bc8b0cf868660405161038492919061065e565b60405180910390a1505050505050565b60008083601f8401126103a657600080fd5b50813567ffffffffffffffff8111156103be57600080fd5b6020830191508360208285010111156101f857600080fd5b600080602083850312156103e957600080fd5b823567ffffffffffffffff81111561040057600080fd5b61040c85828601610394565b90969095509350505050565b82815260006020604081840152835180604085015260005b8181101561044c57858101830151858201606001528201610430565b8181111561045e576000606083870101525b50601f01601f191692909201606001949350505050565b60008060008060006060868803121561048d57600080fd5b853567ffffffffffffffff808211156104a557600080fd5b6104b189838a01610394565b90975095506020880135945060408801359150808211156104d157600080fd5b506104de88828901610394565b969995985093965092949392505050565b8183823760009101908152919050565b600181811c9082168061051357607f821691505b60208210810361053357634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b601f82111561059957600081815260208120601f850160051c810160208610156105765750805b601f850160051c820191505b8181101561059557828155600101610582565b5050505b505050565b815167ffffffffffffffff8111156105b8576105b8610539565b6105cc816105c684546104ff565b8461054f565b602080601f83116001811461060157600084156105e95750858301515b600019600386901b1c1916600185901b178555610595565b600085815260208120601f198616915b8281101561063057888601518255948401946001909101908401610611565b508582101561064e5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60208152816020820152818360408301376000818301604090810191909152601f909201601f1916010191905056fea2646970667358221220fc2bae2b4debff245605d7f907aee00f560f13c579516dfa7fbace7199db12d664736f6c63430008100033";

    public static final String FUNC_ADDEVIDENCE = "addEvidence";

    public static final String FUNC_GETEVIDENCE = "getEvidence";

    public static final String FUNC_OWNER = "owner";

    public static final Event NEWEVIDENCE_EVENT = new Event("NewEvidence", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
    ;

    protected EvidenceRegistry(String contractAddress, Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, bifj, transactionManager, contractGasProvider);
    }

    public List<NewEvidenceEventResponse> getNewEvidenceEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWEVIDENCE_EVENT, transactionReceipt);
        ArrayList<NewEvidenceEventResponse> responses = new ArrayList<NewEvidenceEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewEvidenceEventResponse typedResponse = new NewEvidenceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewEvidenceEventResponse> newEvidenceEventFlowable(CoreFilter filter) {
        return bifj.coreLogFlowable(filter).map(new Function<Log, NewEvidenceEventResponse>() {
            @Override
            public NewEvidenceEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWEVIDENCE_EVENT, log);
                NewEvidenceEventResponse typedResponse = new NewEvidenceEventResponse();
                typedResponse.log = log;
                typedResponse.did = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewEvidenceEventResponse> newEvidenceEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        CoreFilter filter = new CoreFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWEVIDENCE_EVENT));
        return newEvidenceEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addEvidence(byte[] did, byte[] anchorHash, byte[] metadata) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(
                FUNC_ADDEVIDENCE, 
                Arrays.<Type>asList(new DynamicBytes(did),
                new Bytes32(anchorHash),
                new DynamicBytes(metadata)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<byte[], byte[]>> getEvidence(byte[] did) {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(new DynamicBytes(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteFunctionCall<Tuple2<byte[], byte[]>>(function,
                new Callable<Tuple2<byte[], byte[]>>() {
                    @Override
                    public Tuple2<byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final org.bifj.abi.datatypes.Function function = new org.bifj.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static EvidenceRegistry load(String contractAddress, Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EvidenceRegistry(contractAddress, bifj, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EvidenceRegistry> deploy(Bifj bifj, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EvidenceRegistry.class, bifj, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<EvidenceRegistry> deploy(Bifj bifj, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EvidenceRegistry.class, bifj, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EvidenceRegistry> deploy(Bifj bifj, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EvidenceRegistry.class, bifj, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EvidenceRegistry> deploy(Bifj bifj, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EvidenceRegistry.class, bifj, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class NewEvidenceEventResponse extends BaseEventResponse {
        public byte[] did;
    }
}
