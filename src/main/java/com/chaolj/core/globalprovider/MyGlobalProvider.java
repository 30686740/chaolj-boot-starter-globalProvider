package com.chaolj.core.globalprovider;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chaolj.core.MyUser;
import org.springframework.context.ApplicationContext;

import java.util.List;
import com.chaolj.core.MyApp;
import com.chaolj.core.MyConst;
import com.chaolj.core.commonUtils.myDto.DataResultDto;
import com.chaolj.core.commonUtils.myServer.Interface.IGlobalServer;
import com.chaolj.core.commonUtils.myServer.Models.GlobalOptionDto;

public class MyGlobalProvider implements IGlobalServer {
    private ApplicationContext applicationContext;
    private MyGlobalProviderProperties properties;

    public MyGlobalProvider(ApplicationContext applicationContext, MyGlobalProviderProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    private String getHost() {
        return this.properties.getServerHostUrl();
    }

    private String getSSOUserToken() {
        return MyUser.getCurrentUserToken();
    }

    public DataResultDto<String> GlobalParameter(String name) {
        DataResultDto<String> myresult;

        var url = this.getHost() + "/api/globalparameter/getvalue/";
        try {
            myresult = MyApp.Http().url(url)
                    .header(MyConst.HEADERKEY_TOKEN, this.getSSOUserToken())
                    .buildGet().query("name", name)
                    .execute().toJavaObject(new TypeReference<>(){});
        } catch (Exception ex) {
            myresult = DataResultDto.Empty();
            myresult.setResult(false);
            myresult.setMessage("GlobalServer.GlobalParameter，处理失败！" + ex.getMessage());
            myresult.setData("");
        }

        return myresult;
    }

    public DataResultDto<List<GlobalOptionDto>> GlobalOptions(String catalog) {
        DataResultDto<List<GlobalOptionDto>> myresult;

        var url = this.getHost() + "/api/globaloption/getoptions/";
        try {
            myresult = MyApp.Http().url(url)
                    .header(MyConst.HEADERKEY_TOKEN, this.getSSOUserToken())
                    .buildGet().query("catalog", catalog)
                    .execute().toJavaObject(new TypeReference<>() {});
        } catch (Exception ex) {
            myresult = DataResultDto.Empty();
            myresult.setResult(false);
            myresult.setMessage("GlobalServer.GlobalOptions，处理失败！" + ex.getMessage());
            myresult.setData(null);
        }

        return myresult;
    }

    public DataResultDto<String> GlobalSN(String name) {
        DataResultDto<String> myresult;

        var url = this.getHost() + "/api/globalserialnumber/getsn/";
        try {
            myresult = MyApp.Http().url(url)
                    .header(MyConst.HEADERKEY_TOKEN, this.getSSOUserToken())
                    .buildGet().query("name", name)
                    .execute().toJavaObject(new TypeReference<>(){});
        } catch (Exception ex) {
            myresult = DataResultDto.Empty();
            myresult.setResult(false);
            myresult.setMessage("GlobalServer.GlobalSN，处理失败！" + ex.getMessage());
            myresult.setData(null);
        }

        return myresult;
    }
}
