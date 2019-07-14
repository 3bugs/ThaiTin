package th.ac.dusit.dbizcom.thaitin.net;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {

    @GET("get_word")
    Call<GetWordResponse> getWord(
    );

    /*@FormUrlEncoded
    @POST("get_feeding")
    Call<GetFeedingResponse> getFeedingByPond(
            @Field("pondId") int pondId
    );*/

}