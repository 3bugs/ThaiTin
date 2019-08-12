package th.ac.dusit.dbizcom.thaitin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import th.ac.dusit.dbizcom.thaitin.etc.Utils;
import th.ac.dusit.dbizcom.thaitin.fragment.AboutFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.BaseFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.HistoryFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.SentenceFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.SlangFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.WordFragment;
import th.ac.dusit.dbizcom.thaitin.fragment.WordListFragment;
import th.ac.dusit.dbizcom.thaitin.model.Sentence;
import th.ac.dusit.dbizcom.thaitin.model.Slang;
import th.ac.dusit.dbizcom.thaitin.model.Word;

public class MainActivity extends AppCompatActivity implements
        BaseFragment.BaseFragmentListener,
        WordListFragment.WordListFragmentListener,
        SentenceFragment.SentenceFragmentListener,
        SlangFragment.SlangFragmentListener {

    private static final String TAg = MainActivity.class.getName();

    static final String KEY_FRAGMENT = "fragment";
    static final String TAG_FRAGMENT_WORD = "word_fragment";
    static final String TAG_FRAGMENT_SENTENCE = "sentence_fragment";
    static final String TAG_FRAGMENT_SLANG = "slang_fragment";
    static final String TAG_FRAGMENT_HISTORY = "history_fragment";
    static final String TAG_FRAGMENT_ABOUT = "about_fragment";

    private TextView mTitleTextView;
    private BottomNavigationView mNavView;

    protected enum FragmentTransitionType {
        NONE,
        SLIDE,
        FADE
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            setCheckable(mNavView, true);

            switch (item.getItemId()) {
                case R.id.nav_word:
                    loadFragment(
                            new WordFragment(),
                            TAG_FRAGMENT_WORD,
                            false,
                            FragmentTransitionType.FADE
                    );
                    return true;
                case R.id.nav_sentence:
                    loadFragment(
                            new SentenceFragment(),
                            TAG_FRAGMENT_SENTENCE,
                            false,
                            FragmentTransitionType.FADE
                    );
                    return true;
                case R.id.nav_history:
                    loadFragment(
                            new HistoryFragment(),
                            TAG_FRAGMENT_HISTORY,
                            false,
                            FragmentTransitionType.FADE
                    );
                    return true;
                case R.id.nav_slang:
                    loadFragment(
                            new SlangFragment(),
                            TAG_FRAGMENT_SLANG,
                            false,
                            FragmentTransitionType.FADE
                    );
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mTitleTextView = toolbar.findViewById(R.id.title_text_view);
        mNavView = findViewById(R.id.nav_view);
        mNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        String fragmentTag = intent.getStringExtra(KEY_FRAGMENT);

        //Fragment fragment = null;
        int itemId = -1;
        switch (fragmentTag) {
            case TAG_FRAGMENT_WORD:
                //fragment = new WordFragment();
                itemId = R.id.nav_word;
                break;
            case TAG_FRAGMENT_SENTENCE:
                //fragment = new SentenceFragment();
                itemId = R.id.nav_sentence;
                break;
            case TAG_FRAGMENT_SLANG:
                //fragment = new SlangFragment();
                itemId = R.id.nav_slang;
                break;
            case TAG_FRAGMENT_HISTORY:
                //fragment = new HistoryFragment();
                itemId = R.id.nav_history;
                break;
            case TAG_FRAGMENT_ABOUT:
                //fragment = new AboutFragment();
                break;
        }

        /*if (fragment != null) {
            loadFragment(fragment, fragmentTag, false, FragmentTransitionType.NONE);
        }*/

        if (itemId == -1) {
            //mNavView.getMenu().setGroupCheckable(0, true, true);
            setCheckable(mNavView, false);
            loadFragment(new AboutFragment(), TAG_FRAGMENT_ABOUT, false, FragmentTransitionType.NONE);
        } else {
            mNavView.setSelectedItemId(itemId);
        }
    }

    public static void setCheckable(BottomNavigationView view, boolean checkable) {
        final Menu menu = view.getMenu();
        for(int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setCheckable(checkable);
        }
    }

    protected void loadFragment(Fragment fragment, String tag, boolean addToBackStack,
                                FragmentTransitionType transitionType) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (transitionType == FragmentTransitionType.SLIDE) {
            transaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
            );
        } else if (transitionType == FragmentTransitionType.FADE) {
            transaction.setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
            );
        }
        transaction.replace(
                R.id.fragment_container,
                fragment,
                tag
        );
        if (addToBackStack) {
            transaction.addToBackStack(null).commit();
        } else {
            transaction.commit();
        }
    }

    protected void popAllBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    protected void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void setTitle(String title) {
        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }*/
        mTitleTextView.setText(title);
    }

    @Override
    public void setTabsVisible(boolean visible) {
        findViewById(R.id.tab_layout).setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public TabLayout getTabLayout() {
        return findViewById(R.id.tab_layout);
    }

    @Override
    public void onClickWord(Word word) {
        Utils.showShortToast(MainActivity.this, word.toString());
    }

    @Override
    public void onClickSentence(Sentence sentence) {
        Utils.showShortToast(MainActivity.this, sentence.toString());
    }

    @Override
    public void onClickSlang(Slang slang) {
        Utils.showShortToast(MainActivity.this, slang.toString());
    }
}
