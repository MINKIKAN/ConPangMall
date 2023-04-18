package com.console.mall.controller;

import com.console.mall.entitiy.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recomGame")
public class RecomGameController {
    @GetMapping
    public String testAPI(Model model, @RequestParam("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Client-ID", "0dle430ej7oslhsv235qjsf4m49hch");
        headers.set("Authorization", "Bearer cvit9m38vcnnduyjxsyc9xthnsf656");

        String url = "https://api.igdb.com/v4/games";
        String query = "fields name, platforms.name, cover.url, summary, total_rating_count, release_dates.human, genres.name, involved_companies.company.name, game_modes.name, videos.video_id, screenshots.url; where release_dates.platform = " + id + " & total_rating_count > 1000; sort total_sales desc; limit 10;";
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();
        model.addAttribute("responseBody", responseBody);

        // Gson 객체 생성
        Gson gson = new Gson();

        // JSON 문자열을 Game 리스트로 파싱
        Type gameListType = new TypeToken<List<Game>>(){}.getType();
        List<Game> games = gson.fromJson(responseBody, gameListType);

        // Model에 games 리스트를 추가하여 View로 전달
        model.addAttribute("games", games);

        return "recomGame";
    }

//@GetMapping
//public String testAPI(Model model, @RequestParam("id") String id) {
//    RestTemplate restTemplate = new RestTemplate();
//    System.out.println("1");
//    HttpHeaders headers = new HttpHeaders();
//    System.out.println("2");
//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    headers.set("Client-ID", "0dle430ej7oslhsv235qjsf4m49hch");
//    headers.set("Authorization", "Bearer cvit9m38vcnnduyjxsyc9xthnsf656");
//
//    String url = "https://api.igdb.com/v4/games";
//    String query = "fields name, platforms.name, cover.url, summary, total_rating_count, release_dates.human, genres.name, involved_companies.company.name, game_modes.name, videos.video_id, screenshots.url; where release_dates.platform = " + id + " & total_rating_count > 1000; sort total_sales desc; limit 10;";
//    HttpEntity<String> entity = new HttpEntity<>(query, headers);
//
//    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//    System.out.println("response = " + response);
//    String responseBody = response.getBody();
//
//    // 결과를 콘솔 창에 출력
//    System.out.println(responseBody);
//    model.addAttribute("responseBody", responseBody);
//     //Gson 객체 생성
//    Gson gson = new Gson();
//
//    // JSON 문자열을 Game 리스트로 파싱
//    Type gameListType = new TypeToken<List<Game>>(){}.getType();
//    List<Game> games = gson.fromJson(responseBody, gameListType);
//
//    // Model에 games 리스트를 추가하여 View로 전달
//    model.addAttribute("games", games);
//
//    return "recomGame";
//}







}
