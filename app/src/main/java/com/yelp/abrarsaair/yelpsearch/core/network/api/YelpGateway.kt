package assignment.vendawais.twittersearchapi.api

import com.yelp.abrarsaair.yelpsearch.EndPoints
import com.yelp.abrarsaair.yelpsearch.entities.autocomplete.Suggestions
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Detail
import com.yelp.abrarsaair.yelpsearch.entities.search.SearchResults
import io.reactivex.Single
import okhttp3.internal.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpGateway {

    @GET(EndPoints.AUTOCOMPLETE)
    fun getSuggestions(@Query("text") query: String, @Query("latitude") latitude: String, @Query("longitude") longitude: String, @Header("Authorization") header: String = EndPoints.TOKEN) : Single<Response<Suggestions>>

    @GET(EndPoints.SEARCH)
    fun searchBusiness(@Query("term") query: String, @Query("latitude") latitude: String, @Query("longitude") longitude: String,  @Query("sort_by") sort: String = "best_match" , @Header("Authorization") header: String = EndPoints.TOKEN) : Single<Response<SearchResults>>

    @GET(EndPoints.SEARCH)
    fun searchBusinessWithAddress(@Query("term") query: String, @Query("location") location: String,@Query("sort_by") sort: String = "best_match", @Header("Authorization") header: String = EndPoints.TOKEN) : Single<Response<SearchResults>>

    @GET(EndPoints.BUSINESS)
    fun getBusinessDetail(@Path("id") id: String, @Header("Authorization") header: String = EndPoints.TOKEN) : Single<Response<Detail>>
}
