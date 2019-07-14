package th.ac.dusit.dbizcom.thaitin.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.ac.dusit.dbizcom.thaitin.R;

public class WordFragment extends BaseFragment {

    private static final String TAG = WordFragment.class.getName();

    private WordFragmentListener mListener;

    public WordFragment() {
        // Required empty public constructor
        mTitle = "คำศัพท์";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface WordFragmentListener {
    }
}
