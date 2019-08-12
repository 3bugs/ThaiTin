package th.ac.dusit.dbizcom.thaitin.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Slang {

    @SerializedName("id")
    public final int id;
    @SerializedName("slang")
    public final String slang;
    @SerializedName("translation")
    public final String translation;

    public Slang(int id, String slang, String translation) {
        this.id = id;
        this.slang = slang;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s = %s", slang, translation);
    }
}
