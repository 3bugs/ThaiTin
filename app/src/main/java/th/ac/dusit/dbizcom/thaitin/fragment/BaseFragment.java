package th.ac.dusit.dbizcom.thaitin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.ac.dusit.dbizcom.thaitin.R;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getName();
    protected String mTitle = "หัวเรื่อง";

    protected BaseFragmentListener mListener;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mListener != null) {
            mListener.setTitle(mTitle);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragmentListener) {
            mListener = (BaseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface BaseFragmentListener {
        void setTitle(String title);
    }
}
