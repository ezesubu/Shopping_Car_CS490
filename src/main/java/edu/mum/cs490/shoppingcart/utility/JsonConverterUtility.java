/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.shoppingcart.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class JsonConverterUtility {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T jsonToObject(String data, Class<T> clazz) throws IOException, NullPointerException {
        T t = mapper.readValue(data, clazz);
        return t;
    }

    public static <T> String objectToJson(T t) throws JsonProcessingException {
       return mapper.writeValueAsString(t);
    }
}
