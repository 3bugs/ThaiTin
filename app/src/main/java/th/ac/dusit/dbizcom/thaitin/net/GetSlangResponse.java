package th.ac.dusit.dbizcom.thaitin.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import th.ac.dusit.dbizcom.thaitin.model.Slang;

public class GetSlangResponse extends BaseResponse {
    @SerializedName("data_list")
    public List<Slang> slangList;
}