package th.ac.dusit.dbizcom.thaitin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

public class WordCategory {

    @SerializedName("name")
    public final String name;
    @SerializedName("title")
    public final String title;
    @SerializedName("word_list")
    public final List<Word> wordList;

    public WordCategory(String name, String title, List<Word> wordList) {
        this.name = name;
        this.wordList = wordList;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.getDefault(),
                "%s [%d คำ]",
                name, wordList.size()
        );
    }
}
