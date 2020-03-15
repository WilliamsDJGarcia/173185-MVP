package com.example.mvp.Presenter;

import android.view.View;

import com.example.mvp.Model.Product;

import org.json.JSONArray;

public class HomeActivityPrensent {
    private View view;
    private Product product;

    public HomeActivityPrensent(View view){
        this.view = view;
        this.product = new Product();
    }

    public void get(String Token){
        product.getData(Token);
        System.out.println("VALOR DATA "+product.getData());
        view.updateData(product.getData());
    }

    public interface View{
        void updateData(JSONArray data);
    }
}
