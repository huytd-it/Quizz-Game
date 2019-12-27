package com.yukihuy.myapplication.Model;

import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.Model;


public class LoginResponse extends Model {
    @JsonName
    public String token;

    @JsonName
    public String type;

    @JsonName
    public int expires;
}
