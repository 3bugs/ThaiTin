package th.ac.dusit.dbizcom.thaitin.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import th.ac.dusit.dbizcom.thaitin.R;
import th.ac.dusit.dbizcom.thaitin.etc.Utils;
import th.ac.dusit.dbizcom.thaitin.model.Sentence;
import th.ac.dusit.dbizcom.thaitin.net.ApiClient;
import th.ac.dusit.dbizcom.thaitin.net.GetSentenceResponse;
import th.ac.dusit.dbizcom.thaitin.net.MyRetrofitCallback;
import th.ac.dusit.dbizcom.thaitin.net.WebServices;

public class SentenceFragment extends BaseFragment {

    private static final String TAG = SentenceFragment.class.getName();

    private ProgressBar mProgressBar;

    private List<Sentence> mSentenceList = null;
    private SentenceFragmentListener mListener;

    public SentenceFragment() {
        // Required empty public constructor
        mTitle = "สนทนา";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sentence, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progress_bar);
        if (mSentenceList == null) {
            doGetSentence(view);
        } else {
            setupRecyclerView(view);
        }
    }

    private void doGetSentence(final View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);

        Call<GetSentenceResponse> call = services.getSentence();
        call.enqueue(new MyRetrofitCallback<>(
                getActivity(),
                null,
                mProgressBar,
                new MyRetrofitCallback.MyRetrofitCallbackListener<GetSentenceResponse>() {
                    @Override
                    public void onSuccess(GetSentenceResponse responseBody) {
                        mSentenceList = responseBody.sentenceList;
                        setupRecyclerView(view);
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

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        SentenceListAdapter adapter = new SentenceListAdapter(
                view.getContext(),
                mSentenceList,
                mListener
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new SpacingDecoration(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SentenceFragmentListener) {
            mListener = (SentenceFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseFragmentListener");
        }
    }

    public interface SentenceFragmentListener {
        void onClickSentence(Sentence sentence);
    }

    private static class SentenceListAdapter extends RecyclerView.Adapter<SentenceListAdapter.ViewHolder> {

        private final Context mContext;
        private final List<Sentence> mSentenceList;
        private final SentenceFragmentListener mListener;

        SentenceListAdapter(Context context, List<Sentence> sentenceList, SentenceFragmentListener listener) {
            mContext = context;
            mSentenceList = sentenceList;
            mListener = listener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_sentence, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Sentence sentence = mSentenceList.get(position);

            holder.mSentence = sentence;
            holder.mSentenceTextView.setText(sentence.sentence);
            holder.mTranslationTextView.setText(sentence.translation);

            //int rowBgColorRes = position % 2 == 0 ? R.color.row_light_background : R.color.row_dark_background;
            //holder.mRootView.setBackgroundResource(rowBgColorRes);
        }

        @Override
        public int getItemCount() {
            return mSentenceList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mRootView;
            private final TextView mSentenceTextView;
            private final TextView mTranslationTextView;

            private Sentence mSentence;

            ViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mSentenceTextView = itemView.findViewById(R.id.sentence_text_view);
                mTranslationTextView = itemView.findViewById(R.id.translation_text_view);

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onClickSentence(mSentence);
                        }
                    }
                });
            }
        }
    }

    public class SpacingDecoration extends RecyclerView.ItemDecoration {

        private final static int MARGIN_TOP_IN_DP = 8;
        private final static int MARGIN_BOTTOM_IN_DP = 16;

        private final int mMarginTop, mMarginBottom;

        SpacingDecoration(@NonNull Context context) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();

            mMarginTop = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_TOP_IN_DP,
                    metrics
            );
            mMarginBottom = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_BOTTOM_IN_DP,
                    metrics
            );
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            final int itemPosition = parent.getChildAdapterPosition(view);
            if (itemPosition == RecyclerView.NO_POSITION) {
                return;
            }
            if (itemPosition == 0) {
                outRect.top = mMarginTop;
            }
            final RecyclerView.Adapter adapter = parent.getAdapter();
            if ((adapter != null) && (itemPosition == adapter.getItemCount() - 1)) {
                outRect.bottom = mMarginBottom;
            }
        }
    }
}
