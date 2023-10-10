package framework;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.utils.StringUtils;
import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {
    public static Protocol getProtocol(){
        //dubbo spi
//        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
//        Protocol protocol = extensionLoader.getExtension(url.get);
//        return protocol;

        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        iterator.next();

        Protocol protocol = new DubboProtocol();
        return protocol;
        // return iterator.next();

//        //工厂模式
//        //在系统启动时设置属性
//        String name = System.getProperty("protocolName");
//        if(StringUtils.isBlank(name)){
//            name= "http";
//        }
//        switch (name){
//            case "http":
//                return new HttpProtocol();
//            case "dubbo":
//                return new DubboProtocol();
//            default:
//                break;
//        }
//        return new HttpProtocol();
    }
}
