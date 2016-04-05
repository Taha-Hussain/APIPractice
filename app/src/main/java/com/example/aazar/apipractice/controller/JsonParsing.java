package com.example.aazar.apipractice.controller;

import com.example.aazar.apipractice.models.RFQ_API_Object;

import org.json.JSONObject;

/**
 * Created by Aazar on 17-Jan-16.
 */
public class JsonParsing {
    protected static RFQ_API_Object JsonToObject(String JsonString){

        try{
            JSONObject finalObject = new JSONObject(JsonString);
            RFQ_API_Object quote = new RFQ_API_Object();

            quote.setQuote(finalObject.getString("quote"));
            quote.setAuthor(finalObject.getString("author"));
            quote.setCategory(finalObject.getString("category"));

            return quote;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // needs to be implemented
    protected static JSONObject ObjectToJson(){
        return null;
    }
}
