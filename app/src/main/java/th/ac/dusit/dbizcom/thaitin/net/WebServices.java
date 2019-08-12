package th.ac.dusit.dbizcom.thaitin.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("get_word")
    Call<GetWordResponse> getWord(
    );

    @GET("get_sentence")
    Call<GetSentenceResponse> getSentence(
    );

    /*@FormUrlEncoded
    @POST("get_feeding")
    Call<GetFeedingResponse> getFeedingByPond(
            @Field("pondId") int pondId
    );*/

}