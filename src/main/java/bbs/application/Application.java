package bbs.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Application implements ApplicationContextAware {

    private static volatile ApplicationContext sContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        sContext = applicationContext;
    }

    public static Object getBean(String name) {
        return sContext.getBean(name);
    }
}
