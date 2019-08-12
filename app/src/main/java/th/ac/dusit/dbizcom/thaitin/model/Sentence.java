package th.ac.dusit.dbizcom.thaitin.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Sentence {

    @SerializedName("id")
    public final int id;
    @SerializedName("sentence")
    public final String sentence;
    @SerializedName("translation")
    public final String translation;

    public Sentence(int id, String sentence, String translation) {
        this.id = id;
        this.sentence = sentence;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s = %s", sentence, translation);
    }
}
