package com.diguage.dangle.studies.bytebuddy;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by diguage on 16/8/19.
 */
public class Main {

}


class Service {
    void deleteEverything() {
        // delete everything ...
    }
}

class SecuredService extends Service {
    @Override
    void deleteEverything() {
        if (UserHolder.user.equals("ADMIN")) {
            super.deleteEverything();
        } else {
            throw new IllegalStateException("Not authorized");
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Secured {
    String user();
}

class UserHolder {
    static String user;
}

interface Framework {
    <T> T secure(Class<T> type);
}

class HardcodedFrameworkImpl implements Framework {
    @Override
    public <T> T secure(Class<T> type) {
        if (type == Service.class) {
            return (T) new SecuredService();
        } else {
            throw new IllegalArgumentException("Unknown: " + type);
        }
    }
}

