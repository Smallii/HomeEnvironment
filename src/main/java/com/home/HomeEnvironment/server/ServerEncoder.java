package com.home.HomeEnvironment.server;

import net.sf.json.JSONArray;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Map;

public class ServerEncoder implements Encoder.Text<Map<String, Object>> {

    @Override
    public String encode(Map<String, Object> stringObjectMap) throws EncodeException {
        return JSONArray.fromObject(stringObjectMap).toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
