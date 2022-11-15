package util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
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
    private static final String DRIVER_KEY = "db.driver";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final Integer DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;
    private static List<Connection> sourceConnection;

    static {
        loadDriver();
        initConnectionPool();
    }

    private static void initConnectionPool() {
        var poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
        var size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.valueOf(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnection = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[] {Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy)
                            : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnection.add(connection);
        }
    }

    @SneakyThrows
    public static Connection get() {
        return pool.take();
    }

    @SneakyThrows
    public static void closeConnectionPool() {
        for (Connection s : sourceConnection) {
            s.close();
        }
    }

    @SneakyThrows
    private static Connection open() {
        return pool.take();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName(PropertiesUtil.get(DRIVER_KEY));
    }
}
