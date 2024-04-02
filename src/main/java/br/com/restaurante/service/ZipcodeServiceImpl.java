package br.com.restaurante.service;

import br.com.restaurante.shared.ViaCepZipCodeDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ZipcodeServiceImpl {
    public ViaCepZipCodeDto GetZipCode(String zipCode) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + zipCode + "/json/"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ViaCepZipCodeDto dto = new Gson().fromJson(response.body(), ViaCepZipCodeDto.class);
        return dto;
    }
}