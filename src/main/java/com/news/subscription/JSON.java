package com.news.subscription;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public enum JSON {

    MAPPER;

    private ObjectMapper mapper;

    JSON() {
        mapper = new ObjectMapper();
        //mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public byte[] toBytes(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (IOException e) {
            // TODO handle exception ?
            e.printStackTrace();
        }
        return null;
    }

    public <T> T toObject(byte[] payload, Class<T> type) {
        try {
            return mapper.readValue(payload, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
