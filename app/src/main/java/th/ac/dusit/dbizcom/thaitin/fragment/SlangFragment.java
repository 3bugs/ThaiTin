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

public class SlangFragment extends BaseFragment {

    private static final String TAG = SentenceFragment.class.getName();

    private SlangFragmentListener mListener;

    public SlangFragment() {
        // Required empty public constructor
        mTitle = "คำแสลง";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface SlangFragmentListener {
    }
}
