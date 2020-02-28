package com.example.mvp;

import android.view.View;

public class MainActivityPrensent {
    private User user;
    private View view;

    public MainActivityPrensent(View view){
        this.user = new User();
        this.view = view;

    }

    public void update(String Name){
        user.setName(Name);
        view.updateUserTextView(user.toString());

    }

    public interface View{
        void updateUserTextView(String Name);
    }
}
