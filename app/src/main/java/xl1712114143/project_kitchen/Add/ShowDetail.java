package xl1712114143.project_kitchen.Add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import xl1712114143.project_kitchen.MainActivity;
import xl1712114143.project_kitchen.R;

public class ShowDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_show);
        imageView=(ImageView)findViewById(R.id.image);
        content=(TextView)findViewById(R.id.content);
        int image=getIntent().getIntExtra("image",R.drawable.notfound);
        String show=getIntent().getStringExtra("content");
        System.out.println(show);
        imageView.setBackgroundResource(image);
        content.setText(show);
    }
}
