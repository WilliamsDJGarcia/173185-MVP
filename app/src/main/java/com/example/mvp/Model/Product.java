package com.example.mvp.Model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Product {
    AsyncHttpClient client = new AsyncHttpClient();
    Connection con = new Connection();
    String url = con.getUrl()+ "product/";

    private int id;
    private String name;
    private String provider;
    private String netContent;
    private int cost;
    private JSONArray data;

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getNetContent() {
        return netContent;
    }

    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void getData(String Token){
        client.setEnableRedirects(true, true, true);
        client.addHeader("Authorization", "Token "+Token);
        client.get(url+"product_lista", null,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                setData(response);
                System.out.println("STATUS GET AQUI " + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("STATUS MALO"+statusCode);
            }
        });
    }

    public void editData(int id,String Token,String Name,String Provider, String NetContent, int Cost){
        RequestParams p = new RequestParams();
        p.put("name",Name);
        p.put("provider",Provider);
        p.put("net_content",NetContent);
        p.put("cost",Cost);
        client.setEnableRedirects(true, true, true);
        client.addHeader("Authorization", "Token "+Token);
        client.put(url + "product_detail/" + id + "/", p, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("STATUS OK" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("STATUS " + statusCode);
            }
        });
    }

    public void deleteData(int id,String Token){
        client.setEnableRedirects(true, true, true);
        client.addHeader("Authorization", "Token "+Token);
        client.delete(url+"product_detail/"+id+"/", null,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("STATUS DSELETE AQUI " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("STATUS " + statusCode);

            }

        });
    }

    public void postData(String Token,String Name,String Provider, String NetContent, int Cost){
        client.setEnableRedirects(true, true, true);
        client.addHeader("Authorization", "Token "+Token);
        RequestParams p = new RequestParams();
        p.put("name",Name);
        p.put("provider",Provider);
        p.put("net_content",NetContent);
        p.put("cost",Cost);
        client.post(url+"product_lista/", p,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("STATUS " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("STATUS " + statusCode);
            }
        });
    }


}
