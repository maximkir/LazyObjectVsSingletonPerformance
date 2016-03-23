package util;

import java.util.Properties;


public class PropertiesSingleton {

    public static Properties getProperties(){
        return Helper.INSTANCE;
    }

    private final static class Helper{
        private final static Properties INSTANCE = computeWithClassLoaderLock();


        private static Properties computeWithClassLoaderLock(){
            return new Properties();
        }
    }
}
