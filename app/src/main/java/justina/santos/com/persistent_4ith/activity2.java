package justina.santos.com.persistent_4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class activity2 extends AppCompatActivity

{
    TextView tName, tpassword;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity2);
            tName = findViewById(R.id.tvname);
            tpassword = findViewById(R.id.tvpassword);
        }

        public void loadData(View v)
        {
            SharedPreferences pref = getSharedPreferences("Data1", MODE_PRIVATE);
            String uName = pref.getString("user", null);
            String pwd = pref.getString("password", null);
            tName.setText(uName);
            tpassword.setText(pwd);
        }

        public void prevBTN(View v) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }

        public void loadInternal(View v)
        {
            try {
                FileInputStream fis = openFileInput("Data2.txt");
                int read = 0;
                StringBuffer buffer = new StringBuffer();
                while((read = fis.read()) != -1)
                {
                    buffer.append((char)read);
                }
                tName.setText(buffer);

            } catch (Exception e) {
                Log.d("Error", "ERROR READING DATA!");
            }

        }
    public void loadExternal(View v)
    {
        File folder = getExternalFilesDir("MyFolder");
        File file = new File(folder, "Data3.txt");
        try {
            FileInputStream fis = openFileInput("Data3.txt");
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            while((read = fis.read()) != -1)
            {
                buffer.append((char)read);
            }
            tName.setText(buffer);

        } catch (Exception e) {
            Log.d("Error", "ERROR READING DATA!");
        }

    }
}
