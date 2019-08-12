package th.ac.dusit.dbizcom.thaitin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import th.ac.dusit.dbizcom.thaitin.MainActivity;
import th.ac.dusit.dbizcom.thaitin.R;
import th.ac.dusit.dbizcom.thaitin.etc.Utils;
import th.ac.dusit.dbizcom.thaitin.model.WordCategory;
import th.ac.dusit.dbizcom.thaitin.net.ApiClient;
import th.ac.dusit.dbizcom.thaitin.net.GetWordResponse;
import th.ac.dusit.dbizcom.thaitin.net.MyRetrofitCallback;
import th.ac.dusit.dbizcom.thaitin.net.WebServices;

public class WordFragment extends BaseFragment {

    private static final String TAG = WordFragment.class.getName();

    private ProgressBar mProgressBar;

    private List<WordCategory> mWordCategoryList = null;
    private WordFragmentListener mListener;

    public WordFragment() {
        // Required empty public constructor
        mTitle = "คำศัพท์";
        mTabsVisible = false;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progress_bar);
        if (mWordCategoryList == null) {
            doGetWord();
        } else {
            setupViewPager();
        }
    }

    private void doGetWord() {
        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);

        Call<GetWordResponse> call = services.getWord();
        call.enqueue(new MyRetrofitCallback<>(
                getActivity(),
                null,
                mProgressBar,
                new MyRetrofitCallback.MyRetrofitCallbackListener<GetWordResponse>() {
                    @Override
                    public void onSuccess(GetWordResponse responseBody) {
                        mWordCategoryList = responseBody.wordCategoryList;
                        setupViewPager();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Utils.showOkDialog(
                                getActivity(),
                                "Error",
                                errorMessage
                        );
                    }
                }
        ));
    }

    private void setupViewPager() {
        if (getView() == null) return;

        WordCategoryPagerAdapter adapter = new WordCategoryPagerAdapter(
                getChildFragmentManager(),
                getActivity(),
                mWordCategoryList
        );

        ViewPager viewPager = getView().findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getTabLayout().setupWithViewPager(viewPager);
            activity.getTabLayout().setVisibility(View.VISIBLE);
        }
    }

    public interface WordFragmentListener {
    }

    private static class WordCategoryPagerAdapter extends FragmentStatePagerAdapter {

        private Context mContext;
        private List<WordCategory> mWordCategoryList;

        WordCategoryPagerAdapter(FragmentManager fm, Context context, List<WordCategory> wordCategoryList) {
            super(fm);
            this.mContext = context;
            this.mWordCategoryList = wordCategoryList;
        }

        @Override
        public Fragment getItem(int position) {
            return WordListFragment.newInstance(mWordCategoryList.get(position));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mWordCategoryList.get(position).title;
        }

        @Override
        public int getCount() {
            return mWordCategoryList.size();
        }
    }
}
