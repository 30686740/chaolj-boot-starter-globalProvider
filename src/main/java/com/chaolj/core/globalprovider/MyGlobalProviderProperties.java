package com.chaolj.core.globalprovider;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "myproviders.myglobalprovider")
public class MyGlobalProviderProperties {
    private String defaultClientToken = "dev";
    private String serverHostUrl = "https://deverp.ztzs.cn/_global";
}
