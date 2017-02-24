package com.grishberg.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grishberg.data.model.UserEntity;
import freemarker.template.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by grishberg on 20.02.17.
 */
@Getter
@Setter
@JsonIgnoreProperties({"templateConfiguration"})
public class ConfigContext {
    private Configuration templateConfiguration;
    private int port;
    private int lightPin;
    UserEntity[] users;
}

