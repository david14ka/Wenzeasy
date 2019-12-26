package com.wenzeasy.models;

import androidx.annotation.NonNull;

public class RouteArgument {
    private String id;
    private String heroTag;
    private String param;

    public RouteArgument(String id, String heroTag, String param) {
        this.id = id;
        this.heroTag = heroTag;
        this.param = param;
    }

    @NonNull
    @Override
    public String toString() {
        return "{id:"+this.id+",heroTag:"+this.heroTag+"}";
    }
}
