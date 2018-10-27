package justina.santos.com.persistent_4ith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText eName, ePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.etname);
        ePwd = findViewById(R.id.etPassword);
    }


    public void nextBTN(View v) {
            Intent  i = new Intent(this, activity2.class);
            startActivity(i);

    }

    public void saveDate(View v)
    {
        String uName = eName.getText().toString();
        String pwd = ePwd.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("user", uName);
        writer.putString("password", pwd);
        writer.commit();
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternal(View v)
    {
        String uName = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data2.txt", MODE_PRIVATE);
            fos.write(uName.getBytes());
        } catch (Exception e) {
            Log.d("Error", "Error writing to file");
        }
      finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Success, saved in" + getFilesDir(), Toast.LENGTH_SHORT).show();
    }

    public void saveExternal(View v)
    {
        File folder = getExternalFilesDir("MyFolder");
        File file = new File(folder, "Data3.txt");
        String uName = eName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data3.txt", MODE_PRIVATE);
            fos.write(uName.getBytes());
        } catch (Exception e) {
            Log.d("Error", "Error writing to file");
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Success, saved in" + getExternalFilesDir("Data3.txt"), Toast.LENGTH_SHORT).show();

    }
}