import greet.Greet.HelloRequest;
import greet.Greet.HelloReply;
import greet.GreeterGrpc;
import greet.GreeterGrpc.GreeterBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.Scanner;

public class GrpcClient {

	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051).usePlaintext().build();
		
		
		
		GreeterBlockingStub ClientStub=GreeterGrpc.newBlockingStub(channel);

		System.out.print("1. SayHello -Unary");
        System.out.print("2. ParrotSayHello -Server Side Streaming");
      
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 1) {
		
		HelloRequest hello_request = HelloRequest.newBuilder().setGreeting("Good Morning ,Guten Morgen,Bonjour").build();
		
		HelloReply hello_reply = ClientStub.sayHello(hello_request);
		
		System.out.println(hello_reply.getMessage());}
        else if ( num ==2) {
        	HelloRequest hello_request = HelloRequest.newBuilder().setGreeting("Good Morning ,Guten Morgen,Bonjour").build();
        	Iterator<HelloReply> hello_replies = ClientStub.parrotSayHello(hello_request);
        	while(hello_replies.hasNext()) {
        	      System.out.println(hello_replies.next());
        	}
        		
        	
        }
		
		
	}

}