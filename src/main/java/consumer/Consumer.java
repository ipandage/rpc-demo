package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HolleService;

public class Consumer {
    public static void main(String[] args) {
        HolleService proxy = ProxyFactory.getProxy(HolleService.class);
        System.out.println(proxy.sayHello("asdasd"));
    }
}
