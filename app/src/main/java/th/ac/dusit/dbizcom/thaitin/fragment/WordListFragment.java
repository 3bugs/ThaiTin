package th.ac.dusit.dbizcom.thaitin.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import th.ac.dusit.dbizcom.thaitin.R;
import th.ac.dusit.dbizcom.thaitin.model.Word;
import th.ac.dusit.dbizcom.thaitin.model.WordCategory;

public class WordListFragment extends Fragment {

    private static final String TAG = WordListFragment.class.getName();
    private static final String ARG_WORD_CATEGORY_JSON = "word_category_json";

    private WordCategory mWordCategory;
    private WordListFragmentListener mListener;

    public WordListFragment() {
        // Required empty public constructor
    }

    public static WordListFragment newInstance(WordCategory wordCategory) {
        WordListFragment fragment = new WordListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WORD_CATEGORY_JSON, new Gson().toJson(wordCategory));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String wordCategoryJson = getArguments().getString(ARG_WORD_CATEGORY_JSON);
            mWordCategory = new Gson().fromJson(wordCategoryJson, WordCategory.class);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Utils.showShortToast(view.getContext(), String.valueOf(mWordCategory.wordList.size()));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        WordListAdapter adapter = new WordListAdapter(
                view.getContext(),
                mWordCategory.wordList,
                mListener
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new SpacingDecoration(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WordListFragmentListener) {
            mListener = (WordListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement WordListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface WordListFragmentListener {
        void onClickWord(Word word);
    }

    private static class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

        private final Context mContext;
        private final List<Word> mWordList;
        private final WordListFragmentListener mListener;

        WordListAdapter(Context context, List<Word> wordList, WordListFragmentListener listener) {
            mContext = context;
            mWordList = wordList;
            mListener = listener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_word, parent, false
            );
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Word word = mWordList.get(position);

            holder.mWord = word;
            holder.mWordTextView.setText(word.word);
            holder.mTranslationTextView.setText(word.translation);

            int rowBgColorRes = position % 2 == 0 ? R.color.row_light_background : R.color.row_dark_background;
            holder.mRootView.setBackgroundResource(rowBgColorRes);
        }

        private String formatThaiDate(String dateString) {
            String[] datePart = dateString.split("-");
            String day = datePart[2];
            String month = datePart[1];
            String year = String.valueOf(Integer.parseInt(datePart[0]) + 543).substring(2);
            return String.format(Locale.getDefault(), "%s/%s/%s", day, month, year);
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final View mRootView;
            private final TextView mWordTextView;
            private final TextView mTranslationTextView;

            private Word mWord;

            ViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mWordTextView = itemView.findViewById(R.id.word_text_view);
                mTranslationTextView = itemView.findViewById(R.id.translation_text_view);

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onClickWord(mWord);
                    }
                });
            }
        }
    }

    public class SpacingDecoration extends RecyclerView.ItemDecoration {

        private final static int MARGIN_TOP_IN_DP = 0;
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
