package sanitation.la.project.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.format.DateFormat;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sanitation.la.project.myapplication.R;

/**
 * Created by Totten on 4/20/2016.
 */

public class JsonExport extends Activity {
    EditText gridEditText, data1EditText;
    Button button;
    TextView textView;
    String fileName;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_export);
        button = (Button)findViewById(R.id.ebutton);
        textView = (TextView)findViewById(R.id.fileName);
        gridEditText = (EditText)findViewById(R.id.gridEditText); // grid id
        data1EditText = (EditText)findViewById(R.id.data1EditText); // ch4 ppm
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String instantaneous = "instantaneous";
                    fileName = DateFormat.format("yyyy-MM-dd-h-mmssaa", System.currentTimeMillis())
                            .toString();
                    File root = new File(Environment.getExternalStorageDirectory(), "Landfill");
                    //if external memory exists and folder with name Landfill
                    if(!root.exists()) {
                        // creates folder
                        root.mkdirs();
                    }
                    File filepath = new File(root, instantaneous + fileName + ".json");
                    FileWriter writer = new FileWriter(filepath);
                    writer.append(gridEditText.getText().toString());
                    writer.append(data1EditText.getText().toString());
                    writer.flush();
                    writer.close();
                    String test = "File " + instantaneous + fileName + ".json";
                    textView.setText(test);
                } catch (IOException e) {
                    e.printStackTrace();
                    textView.setText(e.getMessage().toString());
                }
            }

        });
    }
}
