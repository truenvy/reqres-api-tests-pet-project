package org.truenvy.reqres.configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:reqresUrlConfig.properties")
public interface ReqresUrlConfig extends Config {

    String users();
}
