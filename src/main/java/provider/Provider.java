package provider;

import framework.URL;
import protocol.http.HttpServer;
import provider.api.HolleService;
import provider.impl.HolleServiceImpl;
import register.RemoteMapRegister;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //1、本地注册
        //{服务名：实现类}
        LocalRegister.regist(HolleService.class.getName(), HolleServiceImpl.class);

        //2、远程注册
        //{服务名：List<URL>}
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HolleService.class.getName(), url);

        //3、启动tomcat
        HttpServer server = new HttpServer();
        server.start("localhost", 8080);
    }
}
