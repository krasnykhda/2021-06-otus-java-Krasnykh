package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.ClientRequest;
import ru.otus.protobuf.generated.RemoteServiceGrpc;
import ru.otus.protobuf.generated.ServerResponse;


import java.util.List;

public class RemoteServiceImpl extends RemoteServiceGrpc.RemoteServiceImplBase {

    private final RealService realService;

    public RemoteServiceImpl(RealService realService) {
        this.realService = realService;
    }


    @Override
    public void getSequence(ClientRequest request, StreamObserver<ServerResponse> responseObserver) {
        List<Long> sequence = realService.getSequence(request.getFirstValue(), request.getLastValue());
        sequence.forEach(u -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(response(u));
        });
        responseObserver.onCompleted();
    }

    private ServerResponse response(Long currentValue) {
        return ServerResponse.newBuilder().setCurrentValue(currentValue)
                .build();
    }
}
