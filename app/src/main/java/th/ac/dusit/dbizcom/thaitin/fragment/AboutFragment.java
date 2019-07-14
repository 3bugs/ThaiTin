package th.ac.dusit.dbizcom.thaitin.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.ac.dusit.dbizcom.thaitin.R;

public class AboutFragment extends BaseFragment {

    private static final String TAG = AboutFragment.class.getName();

    private AboutFragmentListener mListener;

    public AboutFragment() {
        // Required empty public constructor
        mTitle = "เกี่ยวกับภาคอีสาน";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface AboutFragmentListener {
    }
}
