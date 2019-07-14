package th.ac.dusit.dbizcom.thaitin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static th.ac.dusit.dbizcom.thaitin.MainActivity.KEY_FRAGMENT;
import static th.ac.dusit.dbizcom.thaitin.MainActivity.TAG_FRAGMENT_ABOUT;
import static th.ac.dusit.dbizcom.thaitin.MainActivity.TAG_FRAGMENT_HISTORY;
import static th.ac.dusit.dbizcom.thaitin.MainActivity.TAG_FRAGMENT_SENTENCE;
import static th.ac.dusit.dbizcom.thaitin.MainActivity.TAG_FRAGMENT_SLANG;
import static th.ac.dusit.dbizcom.thaitin.MainActivity.TAG_FRAGMENT_WORD;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        findViewById(R.id.word_image_view).setOnClickListener(this);
        findViewById(R.id.sentence_image_view).setOnClickListener(this);
        findViewById(R.id.slang_image_view).setOnClickListener(this);
        findViewById(R.id.history_image_view).setOnClickListener(this);
        findViewById(R.id.about_image_view).setOnClickListener(this);

        findViewById(R.id.search_edit_text).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText et = (EditText) v;

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (et.getRight() - et.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Toast.makeText(DashBoardActivity.this, "Under construction!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        String fragmentTag = null;

        switch (v.getId()) {
            case R.id.word_image_view:
                fragmentTag = TAG_FRAGMENT_WORD;
                break;
            case R.id.sentence_image_view:
                fragmentTag = TAG_FRAGMENT_SENTENCE;
                break;
            case R.id.slang_image_view:
                fragmentTag = TAG_FRAGMENT_SLANG;
                break;
            case R.id.history_image_view:
                fragmentTag = TAG_FRAGMENT_HISTORY;
                break;
            case R.id.about_image_view:
                fragmentTag = TAG_FRAGMENT_ABOUT;
                break;
        }

        if (fragmentTag != null) {
            Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
            intent.putExtra(KEY_FRAGMENT, fragmentTag);
            startActivity(intent);
        }
    }
}
