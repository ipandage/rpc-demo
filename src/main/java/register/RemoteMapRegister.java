package register;

import framework.URL;

import java.io.*;
import java.util.*;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) throws IOException {
        List<URL> list = Collections.singletonList(url);
        REGISTER.put(interfaceName, list);
        saveFile();
    }

    public static URL random(String interfaceName) throws IOException, ClassNotFoundException {
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

    private static void saveFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/temp.text");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(REGISTER);
    }

    private static Map<String, List<URL>> getFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/temp.text");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Map<String, List<URL>>) objectInputStream.readObject();
    }
}
