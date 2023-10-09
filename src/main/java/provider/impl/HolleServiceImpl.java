package provider.impl;

import provider.api.HolleService;

public class HolleServiceImpl implements HolleService {
    @Override
    public String sayHello(String userName) {
        return "hello" + userName;
    }
}
