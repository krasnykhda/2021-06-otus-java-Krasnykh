package ru.otus.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import ru.otus.protobuf.generated.ClientRequest;
import ru.otus.protobuf.generated.RemoteServiceGrpc;
import ru.otus.protobuf.generated.ServerResponse;

import java.util.concurrent.CountDownLatch;

public class GRPCClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;
    private static long lastValueFromServer;
    private static long currentValue = 0;
    private static boolean valueFromServerBeenAdded;
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        RemoteServiceGrpc.RemoteServiceBlockingStub stub = RemoteServiceGrpc.newBlockingStub(channel);


        CountDownLatch latch = new CountDownLatch(1);
        RemoteServiceGrpc.RemoteServiceStub newStub = RemoteServiceGrpc.newStub(channel);
        newStub.getSequence(ClientRequest.newBuilder()
                .setFirstValue(1).setLastValue(10).build(), new StreamObserver<ServerResponse>() {
            @Override
            public void onNext(ServerResponse message) {
                System.out.println("Value from server: " + message.getCurrentValue());
                synchronized (obj) {
                    lastValueFromServer = message.getCurrentValue();
                    valueFromServerBeenAdded = false;
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("\n\nЯ все!");
                latch.countDown();
            }
        });
        for (int i = 1; i <= 50; i++) {
            if (!valueFromServerBeenAdded) {
                synchronized (obj) {
                    valueFromServerBeenAdded = true;
                    currentValue = currentValue + lastValueFromServer + 1;
                }
            } else {
                currentValue++;
            }
            System.out.println("Current value: " + currentValue);
            Thread.sleep(1000);
        }
        latch.await();

        channel.shutdown();
    }
}
