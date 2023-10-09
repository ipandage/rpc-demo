package protocol.dubbo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author shenbin
 * @date 2020/7/23 12:34
 */
public class NettyClient<T> {
    public NettyClientHandler client = null;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostName, Integer port){
        client = new NettyClientHandler();

        Bootstrap b = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                    pipeline.addLast("encoder", new ObjectEncoder());
                    pipeline.addLast("handler", client);
                }
            });
        try {
            b.connect(hostName, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String send(String hostName, Integer port, Invocation invocation){
        if(client == null){
            start(hostName, port);
        }
        //client.setInvocation(invocation);
        try {
            //return (String)executorService.submit(client).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
