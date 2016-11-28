package julliet.example.com.itshappening;


import android.support.v7.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ViewEvent extends AppCompatActivity implements View.OnClickListener {

    private  String email = "Birir@gmail.com";
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private EditText editTextCategory;
    private EditText editTextVenue;
    private EditText editTextImage;

    private Button buttonUpdate;
    private Button buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);
        String name = intent.getStringExtra(Config.EMP_NAME);
        String desc = intent.getStringExtra(Config.EMP_DESCRIPTION);
        String venue = intent.getStringExtra(Config.EMP_VENUE);
        String price = intent.getStringExtra(Config.EMP_PRICE);
        String category = intent.getStringExtra(Config.EMP_CATEGORY);
        String image = intent.getStringExtra(Config.EMP_IMAGE);
        String userid = intent.getStringExtra(Config.USER_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) findViewById(R.id.editTextDecription);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextVenue = (EditText) findViewById(R.id.editTextVenue);
        editTextImage = (EditText) findViewById(R.id.editTextImage);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        if(!email.toLowerCase().trim().equals(userid.toLowerCase().trim())){
            buttonUpdate.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
        }

        editTextId.setText(id);
        editTextName.setText(name);
        editTextDescription.setText(desc);
        editTextPrice.setText(price);
        editTextCategory.setText(category);
        editTextVenue.setText(venue);
        editTextImage.setText(image);
    }

    private void updateEmployee() {
        final String name = editTextName.getText().toString().trim();
        final String desg = editTextPrice.getText().toString().trim();
        final String salary = editTextDescription.getText().toString().trim();
        final String category = editTextCategory.getText().toString().trim();
        final String venue = editTextVenue.getText().toString().trim();
        final String image = editTextImage.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEvent.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEvent.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID, id);
                hashMap.put(Config.KEY_EMP_NAME, name);
                hashMap.put(Config.KEY_EMP_PRICE, desg);
                hashMap.put(Config.KEY_EMP_DESCRIPTION, salary);
                hashMap.put(Config.KEY_EMP_CATEGORY, category);
                hashMap.put(Config.KEY_EMP_VENUE, venue);
                hashMap.put(Config.KEY_EMP_IMAGE, image);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee() {
        class DeleteEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEvent.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEvent.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String query = Config.URL_DELETE_EMP+"?id="+id;
                String s = rh.sendGetRequest(query);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewEvent.this, AllEventsActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdate) {
            updateEmployee();
        }

        if (v == buttonDelete) {
            confirmDeleteEmployee();
        }
    }
}
