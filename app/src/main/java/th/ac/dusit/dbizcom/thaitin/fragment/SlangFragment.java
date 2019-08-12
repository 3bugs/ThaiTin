package th.ac.dusit.dbizcom.thaitin.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import th.ac.dusit.dbizcom.thaitin.R;
import th.ac.dusit.dbizcom.thaitin.etc.Utils;
import th.ac.dusit.dbizcom.thaitin.model.Slang;
import th.ac.dusit.dbizcom.thaitin.net.ApiClient;
import th.ac.dusit.dbizcom.thaitin.net.GetSlangResponse;
import th.ac.dusit.dbizcom.thaitin.net.MyRetrofitCallback;
import th.ac.dusit.dbizcom.thaitin.net.WebServices;

public class SlangFragment extends BaseFragment {

    private static final String TAG = SlangFragment.class.getName();

    private ProgressBar mProgressBar;

    private List<Slang> mSlangList = null;
    private SlangListAdapter mAdapter;
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

        mProgressBar = view.findViewById(R.id.progress_bar);
        if (mSlangList == null) {
            doGetSlang(view);
        } else {
            setupRecyclerView(view);
            setupSearchEditText(view);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupSearchEditText(View view) {
        final EditText searchEditText = view.findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchTerm = s.toString().trim();
                if (mAdapter != null) {
                    mAdapter.filter(searchTerm);
                }
                if (searchTerm.length() > 0) {
                    searchEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_search, 0, R.drawable.ic_close, 0);
                } else {
                    searchEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_search, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= searchEditText.getRight() - searchEditText.getTotalPaddingRight()) {
                        // your action for drawable click event
                        searchEditText.setText("");
                        if (getActivity() != null) {
                            Utils.hideKeyboard(getActivity());
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void doGetSlang(final View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);

        Call<GetSlangResponse> call = services.getSlang();
        call.enqueue(new MyRetrofitCallback<>(
                getActivity(),
                null,
                mProgressBar,
                new MyRetrofitCallback.MyRetrofitCallbackListener<GetSlangResponse>() {
                    @Override
                    public void onSuccess(GetSlangResponse responseBody) {
                        mSlangList = responseBody.slangList;
                        setupRecyclerView(view);
                        setupSearchEditText(view);
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
        mAdapter = new SlangListAdapter(
                view.getContext(),
                mSlangList,
                mListener
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new SpacingDecoration(view.getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SlangFragmentListener) {
            mListener = (SlangFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseFragmentListener");
        }
    }

    public interface SlangFragmentListener {
        void onClickSlang(Slang slang);
    }

    private static class SlangListAdapter extends RecyclerView.Adapter<SlangListAdapter.ViewHolder> {

        private final Context mContext;
        private final List<Slang> mOriginalSlangList;
        private final List<Slang> mSlangList;
        private final SlangFragmentListener mListener;

        SlangListAdapter(Context context, List<Slang> slangList, SlangFragmentListener listener) {
            mContext = context;
            mOriginalSlangList = slangList;
            mSlangList = new ArrayList<>(slangList);
            mListener = listener;
        }

        void filter(String searchTerm) {
            mSlangList.clear();
            for (Slang slang : mOriginalSlangList) {
                if (slang.slang.contains(searchTerm)) {
                    mSlangList.add(slang);
                }
            }
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_slang, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Slang slang = mSlangList.get(position);

            holder.mSlang = slang;
            holder.mSlangTextView.setText(slang.slang);
            holder.mTranslationTextView.setText(slang.translation);

            int rowBgColorRes = position % 2 == 0 ? R.color.row_light_background : R.color.row_dark_background;
            holder.mRootView.setBackgroundResource(rowBgColorRes);
        }

        @Override
        public int getItemCount() {
            return mSlangList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mRootView;
            private final TextView mSlangTextView;
            private final TextView mTranslationTextView;

            private Slang mSlang;

            ViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mSlangTextView = itemView.findViewById(R.id.slang_text_view);
                mTranslationTextView = itemView.findViewById(R.id.translation_text_view);

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onClickSlang(mSlang);
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
