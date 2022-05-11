package com.example.recipeapi;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    @Transactional
    public void delete(IngredientDto ingredientDto) {
        Ingredient test = ingredientRepository.findByIngredient(ingredientDto.getIngredient()).orElseThrow(
                () -> new IllegalArgumentException("재료가 존재하지 않습니다"));
        ingredientRepository.delete(test);
    }

    //재료에 대한 키워드를 받아 관련된 음식 찾아주기
    public List<String> findmenu(String keyword) {
        String query = keyword;

        StringBuffer result = new StringBuffer();
        System.out.println(query);

        try {
            String urlstr = "http://openapi.foodsafetykorea.go.kr/api/25b74e68b0dd4087b4bc/COOKRCP01/json/1/100/RCP_PARTS_DTLS=" + query;

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }

            urlConnection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsingJSON(result);
    }

    //찾은 재료 정보를 JSON으로 파싱 후 list 에 담는 과정
    public List<String> parsingJSON(StringBuffer result) {

        ArrayList<String> list = new ArrayList<>();

        try {

            JSONObject jObj;
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject) jsonParser.parse(result.toString());

            JSONObject parseresponse = (JSONObject) jsonObj.get("COOKRCP01");

            JSONArray array = (JSONArray) parseresponse.get("row");

            for (Object o : array) {
                jObj = (JSONObject) o;

                String name = jObj.get("RCP_NM").toString();
                String img = jObj.get("ATT_FILE_NO_MAIN").toString();

                list.add(name);
                list.add(img);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }

    //해당 음식에 대한 레시피 추출
    public List<String> menurecipe(String menu) {
        StringBuffer result = new StringBuffer();
        ArrayList<String> list = new ArrayList<>();

        String finaltest = "";

        if (menu.contains(" ")) {
            String test = menu.replaceAll("\\s", "_");
            finaltest = test;
            System.out.println(finaltest);
        } else {
            finaltest = menu;
        }


        try {
            String urlstr = "http://openapi.foodsafetykorea.go.kr/api/25b74e68b0dd4087b4bc/COOKRCP01/json/0/1/RCP_NM=" + finaltest;


            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }

            urlConnection.disconnect();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return parsingJSONrcp(result);

    }

    //찾은 레시피를 JSON으로 파싱 후 LIST에 담는 과정
    public List<String> parsingJSONrcp(StringBuffer result) {

        ArrayList<String> list = new ArrayList<>();

        try {

            JSONObject jObj;
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject) jsonParser.parse(result.toString());

            JSONObject parseresponse = (JSONObject) jsonObj.get("COOKRCP01");

            JSONArray array = (JSONArray) parseresponse.get("row");

            for (Object o : array) {
                jObj = (JSONObject) o;

                String name = jObj.get("RCP_NM").toString();
                String img = jObj.get("ATT_FILE_NO_MAIN").toString();
                String menual1 = jObj.get("MANUAL01").toString();
                String menual2 = jObj.get("MANUAL02").toString();
                String menual3 = jObj.get("MANUAL03").toString();
                String menual4 = jObj.get("MANUAL04").toString();
                String menual5 = jObj.get("MANUAL05").toString();
                String menual6 = jObj.get("MANUAL06").toString();
                String menual7 = jObj.get("MANUAL07").toString();
                String menual8 = jObj.get("MANUAL08").toString();
                String menual9 = jObj.get("MANUAL09").toString();

                String ingredient = jObj.get("RCP_PARTS_DTLS").toString();

                list.add(name);
                list.add(img);
                list.add(ingredient);
                list.add(menual1);
                list.add(menual2);
                list.add(menual3);
                list.add(menual4);
                list.add(menual5);
                list.add(menual6);
                list.add(menual7);
                list.add(menual8);
                list.add(menual9);
            }

        } catch (NullPointerException e) {
            System.out.println("Recipie_info NullPointerException 발생  >>  '");
        } catch (ArithmeticException e) {
            System.out.println("Recipie_info ArithmeticException 발생  >>  '");
        } catch (Exception e) {
            System.out.println("Recipie_info Exception 발생  >>  '");
            e.printStackTrace();
        } finally {
            System.out.println("Recipie_info 예외처리 통과");

        }
        return list;
    }

}