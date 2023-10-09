package framework;

public interface Protocol {
    void start(URL url);
    void send(URL url, Invocation invocation);
}
