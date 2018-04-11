package com.example.a04_11;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AssetManager assetManager = null;

    public void printStudent(List<Student> studentList){
        if (studentList != null){
            for (Student student : studentList){
                Log.e("MSG:","STUDENT  ID:"+student.getId()+
                             "  |  NAMR:"+student.getName()+
                            "  |  AGE:"+student.getAge());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getAssets();
        Button button = findViewById(R.id.btn_student);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream  inputStream =
                            assetManager.open("student.xml");
                    List<Student> studentList =
                            StudentDocuService.getStudentFromXML(inputStream);
                    printStudent(studentList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
