package api

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

class ApiController {
    companion object {
        private val mainAddress = "http://localhost:8090";
        private val client = HttpClient.newBuilder().build();
        @JvmStatic
        fun findSportsmans(text: String?, limit: Int): JsonArray {
            var res: String
            var params = mapOf("limit" to limit.toString())
            if (text != null && text.isNotEmpty()) {
                params += "text" to text
            }
            val urlParams = params.map {(k, v) -> "${k}=${v}"}
                .joinToString("&")
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/sportsmans/search?${urlParams}")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }

        }
        @JvmStatic
        fun findBuildings(text: String?, limit: Int): JsonArray {
            var res: String
            var params = mapOf("limit" to limit.toString())
            if (text != null && text.isNotEmpty()) {
                params += "text" to text
            }
            val urlParams = params.map {(k, v) -> "${k}=${v}"}
                .joinToString("&")
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/buildings/search?${urlParams}")).build()
            println(text)
            println(request.uri().toString())
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }

        }
        @JvmStatic
        fun findTrainers(text: String?, limit: Int): JsonArray {
            var res: String
            var params = mapOf("limit" to limit.toString())
            if (text != null && text.isNotEmpty()) {
                params += "text" to text
            }
            val urlParams = params.map {(k, v) -> "${k}=${v}"}
                .joinToString("&")
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/trainers/search?${urlParams}")).build()
            println(text)
            println(request.uri().toString())
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }

        }
        @JvmStatic
        fun findCompetitions(text: String?, limit: Int): JsonArray {
            var res: String
            var params = mapOf("limit" to limit.toString())
            if (text != null && text.isNotEmpty()) {
                params += "text" to text
            }
            val urlParams = params.map {(k, v) -> "${k}=${v}"}
                .joinToString("&")
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/competitions/search?${urlParams}")).build()
            println(text)
            println(request.uri().toString())
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }

        }
        @JvmStatic
        fun getSportsman(id : Int) : JsonObject{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/sportsmans/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonObject
            }
            else{
                return JsonObject()
            }
        }
        @JvmStatic
        fun getBuilding(id : Int) : JsonObject{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/buildings/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonObject
            }
            else{
                return JsonObject()
            }
        }
        @JvmStatic
        fun getTrainer(id : Int) : JsonObject{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/trainers/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonObject
            }
            else{
                return JsonObject()
            }
        }
        @JvmStatic
        fun getCompetition(id : Int) : JsonObject{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/competitions/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonObject
            }
            else{
                return JsonObject()
            }
        }
        /*@JvmStatic
        fun putSportsman(sportsman : JsonObject) : String{
            val id : Int
            try{
                id = sportsman.get("id").asInt;
            }
            catch (e : Exception){
                e.printStackTrace();
                return "fail"
            }
            val requestBody: String = URLEncoder.encode(sportsman.toString(), StandardCharsets.UTF_8.toString());
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/sportsmans/$id"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return "ok"
            }
            else{
                return "not ok"
            }
        }*/
        @JvmStatic
        fun getSportsmanClub(id : Int) : JsonObject{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/sportsmans/$id/club")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonObject
            }
            else{
                return JsonObject()
            }
        }
        @JvmStatic
        fun getSportsmanTrainers(id : Int) : JsonArray{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/coaching/sportsman/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                var arr =  JsonParser.parseString(response.body()).asJsonArray
                val res = JsonArray()
                for (el in arr){
                    res.add(el.asJsonObject.get("trainer"))
                }
                return res

            }
            else{
                return JsonArray()
            }
        }
        @JvmStatic
        fun getTrainerSportsmans(id : Int) : JsonArray{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/coaching/trainer/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                var arr =  JsonParser.parseString(response.body()).asJsonArray
                val res = JsonArray()
                for (el in arr){
                    res.add(el.asJsonObject.get("sportsman"))
                }
                return res

            }
            else{
                return JsonArray()
            }
        }
        @JvmStatic
        fun getBuildingCompetitions(id : Int) : JsonArray{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/competitions/building/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }
        }
        @JvmStatic
        fun getSportsmanResults(id : Int) : JsonArray{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/results/sportsman/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }
        }
        @JvmStatic
        fun getCompetitionResults(id : Int) : JsonArray{
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$mainAddress/results/competition/$id")).build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).asJsonArray
            }
            else{
                return JsonArray()
            }
        }

    }
}