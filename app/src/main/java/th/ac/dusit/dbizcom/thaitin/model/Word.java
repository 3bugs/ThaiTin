package th.ac.dusit.dbizcom.thaitin.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Word {

    @SerializedName("id")
    public final int id;
    @SerializedName("word")
    public final String word;
    @SerializedName("translation")
    public final String translation;
    @SerializedName("category")
    public final String category;

    public Word(int id, String word, String translation, String category) {
        this.id = id;
        this.word = word;
        this.translation = translation;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s = %s", word, translation);
    }
}
