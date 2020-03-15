package com.example.mvp.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.Presenter.HomeActivityPrensent;
import com.example.mvp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements HomeActivityPrensent.View  {
    private HomeActivityPrensent prensent;
    private TableLayout tbl;
    private Button rfh;
    private String Token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        Token = getIntent().getStringExtra("Token");
        tbl = findViewById(R.id.linearLayoutRecords);
        rfh = findViewById(R.id.refresh);
        prensent = new HomeActivityPrensent(this);

        //rfh.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //prensent.get(Token);
            //}
        //});
        prensent.get(Token);
    }

    public void updateData(final JSONArray data){

        if(data !=null){
            Toast.makeText(getApplicationContext(), "LLenando tabla", Toast.LENGTH_SHORT).show();
            for (int i=0; i< data.length();i++){
                JSONObject c = null;
                try {
                    c = data.getJSONObject(i);
                    int cId = c.getInt("id");
                    String cN = c.getString("name");
                    String cY = c.getString("provider");
                    String content = c.getString("net_content");
                    int cost = c.getInt("cost");

                    TextView t = new TextView(getApplicationContext());
                    TextView t2 = new TextView(getApplicationContext());
                    TextView t3 = new TextView(getApplicationContext());

                    t.setText(String.valueOf(cId));
                    t2.setText(cN);
                    t3.setText(cY);

                    TableRow row = new TableRow(getApplicationContext());
                    final Button edit = new Button(getApplicationContext());
                    edit.setTag(cId);
                    edit.setText("Detalles");
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                for (int j =0; j < data.length(); j++){
                                    JSONObject s = data.getJSONObject(j);
                                    int id = s.getInt("id");
                                    if (id==(int)edit.getTag()){
                                        String name = s.getString("name");
                                        String provider = s.getString("provider");
                                        edit(id,name,provider);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    row.addView(t);
                    row.addView(t2);
                    row.addView(t3);
                    row.addView(edit);
                    tbl.addView(row);
                    tbl.setStretchAllColumns(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            prensent.get(Token);
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }
    }

    public void edit(int id, String name, String provider){
        LayoutInflater inflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.content2_activity, null, false);
        final EditText editTextname = (EditText) formElementsView.findViewById(R.id.editTextname);
        final EditText editTextprovider = (EditText) formElementsView.findViewById(R.id.editTextprovider);
        editTextname.setText(name);
        editTextprovider.setText(provider);

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setView(formElementsView);
        builder.setTitle("Details");
        builder.show();
    }
}
