import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.awt.Image
import java.io.File
import java.io.IOException
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture
import javax.imageio.ImageIO


class MyHttpClient {
    private val GRAPHHOPPER_KEY = "9482c7a2-cbbf-4a3e-8fc2-64fdef22bee0"
    private val OPENWEATHERMAP_KEY = "0e04701b1054411b374b27b13faeee23"
    private val OPENTRIPMAP_KEY = "5ae2e3f221c38a28845f05b6049aad568faac60e2fb6d5cc250ce363"
    private val client = HttpClient.newBuilder().build()

    fun getLocationsList(name: String, limit: Int): CompletableFuture<JsonArray> {
        val gson = Gson()
        val params = mapOf("key" to GRAPHHOPPER_KEY, "q" to name, "limit" to limit)
        val urlParams = params.map { (k, v) -> "${k}=${v}" }
            .joinToString("&")
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://graphhopper.com/api/1/geocode?${urlParams}"))
            .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply {
                val body = it.body()
                try {
                    Gson().fromJson(body, JsonObject::class.java).get("hits").asJsonArray
                } catch (e: JsonSyntaxException) {
                    println("Error with json")
                    println(e)
                    JsonArray()
                }
            }
    }

    fun getWeatherHTML(lon: Double, lat: Double): CompletableFuture<String> {
        val params = mapOf(
            "appid" to OPENWEATHERMAP_KEY, "lon" to lon.toString(), "lat" to lat.toString(),
            "units" to "metric", "mode" to "html"
        )
        val urlParams = params.map { (k, v) -> "${k}=${v}" }
            .joinToString("&")
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?${urlParams}"))
            .build();
        val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply {
            it.body()
        }
        return response
    }


    fun getLandmarks(lon: Double, lat: Double, radius: Int): CompletableFuture<JsonArray> {
        val params = mapOf(
            "apikey" to OPENTRIPMAP_KEY, "format" to "json",
            "lon" to lon.toString(), "lat" to lat.toString(), "radius" to radius.toString()
        )
        val urlParams = params.map { (k, v) -> "${k}=${v}" }
            .joinToString("&")
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://api.opentripmap.com/0.1/ru/places/radius?${urlParams}"))
            .build();
        val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply {
            it.body()
        }
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply {
                val body = it.body()
                try {
                    Gson().fromJson(body, JsonArray::class.java)
                } catch (e: JsonSyntaxException) {
                    println("Error with json")
                    println(e)
                    JsonArray()
                }
            }
    }

    fun getLandmarkInfo(id: String): CompletableFuture<JsonObject> {
        val params = mapOf("apikey" to OPENTRIPMAP_KEY)
        val urlParams = params.map { (k, v) -> "${k}=${v}" }
            .joinToString("&")
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("https://api.opentripmap.com/0.1/ru/places/xid/$id?${urlParams}"))
            .build();
        val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        return response.thenApply { Gson().fromJson(it.body(), JsonObject::class.java) }

    }

}