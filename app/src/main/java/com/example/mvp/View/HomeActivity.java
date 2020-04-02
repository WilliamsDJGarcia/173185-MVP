package com.example.mvp.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.mvp.Model.Product;
import com.example.mvp.Presenter.HomeActivityPrensent;
import com.example.mvp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements HomeActivityPrensent.View  {
    private HomeActivityPrensent prensent;
    private TableLayout tbl;
    private TextView rfh;
    private String Token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        Token = getIntent().getStringExtra("Token");
        tbl = findViewById(R.id.linearLayoutRecords);
        rfh = findViewById(R.id.refresh);
        prensent = new HomeActivityPrensent(this);

        rfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.content2_activity, null, false);
                final EditText editTextname = (EditText) formElementsView.findViewById(R.id.editTextname);
                final EditText editTextprovider = (EditText) formElementsView.findViewById(R.id.editTextprovider);
                final EditText editTextnet = (EditText) formElementsView.findViewById(R.id.editTextnet);
                final EditText editTextcost = (EditText) formElementsView.findViewById(R.id.editTextcost);

                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Agregar")
                        .setPositiveButton("Listo",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String name = editTextname.getText().toString();
                                        String provi = editTextprovider.getText().toString();
                                        String netC = editTextnet.getText().toString();
                                        int cost = Integer.parseInt(editTextcost.getText().toString());

                                        prensent.post(Token,name,provi,netC,cost);
                                        dialog.cancel();
                                    }
                                }).show();
            }
        });
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
                    TextView t4 = new TextView(getApplicationContext());
                    TextView t5 = new TextView(getApplicationContext());

                    t.setText(String.valueOf(cId));
                    t2.setText(cN);
                    t3.setText(cY);
                    t4.setText(content);
                    t5.setText(cost);

                    TableRow row = new TableRow(getApplicationContext());
                    final Button edit = new Button(getApplicationContext());
                    final Button delete = new Button(getApplicationContext());
                    edit.setTag(cId);
                    edit.setText("Editar");
                    delete.setTag(cId);
                    delete.setText("Eliminar");
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
                                        String net = s.getString("net_content");
                                        int costo = s.getInt("cost");

                                        edit(id,name,provider,net,costo,v);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                for (int j =0; j < data.length(); j++){
                                    JSONObject s = data.getJSONObject(j);
                                    int id = s.getInt("id");
                                    if (id==(int)edit.getTag()){
                                        delete(id);
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
                    row.addView(t4);
                    row.addView(t5);
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

    public void edit(int id, final String name, String provider, String net_content, int cost, View view){
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.content2_activity, null, false);
        final EditText editTextname = (EditText) formElementsView.findViewById(R.id.editTextname);
        final EditText editTextprovider = (EditText) formElementsView.findViewById(R.id.editTextprovider);
        final EditText editTextnet = (EditText) formElementsView.findViewById(R.id.editTextnet);
        final EditText editTextcost = (EditText) formElementsView.findViewById(R.id.editTextcost);
        editTextname.setText(name);
        editTextprovider.setText(provider);
        editTextnet.setText(net_content);
        editTextcost.setText(String.valueOf(cost));
        final Product s = new Product();
        s.setId(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementsView);
        builder.setTitle("Editar");
        builder.setPositiveButton("Actualizar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = editTextname.getText().toString();
                        String provi = editTextprovider.getText().toString();
                        String netC = editTextnet.getText().toString();
                        int cost = Integer.parseInt(editTextcost.getText().toString());

                        prensent.edit(s.getId(),Token,name,provi,netC,cost);
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    public void delete(int id){
        final Product s = new Product();
        s.setId(id);

        prensent.delete(s.getId(),Token);
    }
}
