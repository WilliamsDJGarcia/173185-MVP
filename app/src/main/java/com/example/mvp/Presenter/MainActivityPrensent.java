package com.example.mvp.Presenter;

import com.example.mvp.Model.User;

public class MainActivityPrensent {
    private User user;
    private View view;

    public MainActivityPrensent(View view){
        this.user = new User();
        this.view = view;
    }

    public void update(String Name,String Password){
        user.setName(Name);
        user.setPassword(Password);
        user.into();
        view.updateUserTextView(user.getToken());
    }

    public interface View{
        void updateUserTextView(String token);
    }
}
