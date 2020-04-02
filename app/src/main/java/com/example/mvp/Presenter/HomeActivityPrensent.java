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

    public void post(String Token,String Name,String Provider, String NetContent, int Cost){
        product.postData(Token,Name,Provider,NetContent,Cost);
        product.getData(Token);
        view.updateData(product.getData());
    }
    public void edit(int id,String Token,String Name,String Provider, String NetContent, int Cost){
        product.editData(id,Token,Name,Provider,NetContent,Cost);
        product.getData(Token);
        view.updateData(product.getData());
    }
    public void delete(int id,String Token){
        product.deleteData(id,Token);
        product.getData(Token);
        view.updateData(product.getData());
    }

    public interface View{
        void updateData(JSONArray data);
    }
}
