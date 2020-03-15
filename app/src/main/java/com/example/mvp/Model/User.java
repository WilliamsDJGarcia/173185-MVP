package com.example.mvp.Model;
// MODELO USER

import android.content.Intent;
import android.widget.TableLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class User {
    AsyncHttpClient client = new AsyncHttpClient();
    Connection con = new Connection();
    String url = con.getUrl()+ "login/";
    private String token;
    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void into(){
        RequestParams p = new RequestParams();
        p.put("username",getName());
        p.put("password",getPassword());
        client.post(url, p,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    setToken(response.getString("token"));
                    System.out.println("STATUS LOGIN AQUI "+getName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("STATUS " + statusCode);
            }
        });
    }

}
