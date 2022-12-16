package dao.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";


    static {
        loadDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        var poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.valueOf(poolSize);
        pool = new ArrayBlockingQueue<>(size);

            var connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[] {Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(proxyConnection);

                PropertiesUtil.get(PASSWORD_KEY));
    }

    @SneakyThrows
    private static void loadDriver() {

}
