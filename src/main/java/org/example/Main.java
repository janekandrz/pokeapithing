package org.example;

import process.ProcessRequest;

import java.net.http.HttpRequest;

public class Main {
    public static void main(String[] args) {
        ProcessRequest handeler = new ProcessRequest();

        String url = "https://pokeapi.co/api/v2/pokemon/ditto";
        String res = handeler.sendGetReq(url);

        System.out.println(res);
    }
}