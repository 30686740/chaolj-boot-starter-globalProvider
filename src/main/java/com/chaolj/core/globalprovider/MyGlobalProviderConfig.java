package com.chaolj.core.globalprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.chaolj.core.commonUtils.myServer.Interface.IGlobalServer;

@Configuration
@EnableConfigurationProperties(MyGlobalProviderProperties.class)
public class MyGlobalProviderConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MyGlobalProviderProperties myGlobalProviderProperties;

    @Bean(name = "myGlobalProvider")
    public IGlobalServer MyGlobalProvider(){
        return new MyGlobalProvider(this.applicationContext, this.myGlobalProviderProperties);
    }
}
