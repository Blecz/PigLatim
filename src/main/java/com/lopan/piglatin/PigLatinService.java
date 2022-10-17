package com.lopan.piglatin;

import org.springframework.stereotype.Service;

@Service
public class PigLatinService {

    private static final String VOWELS = "[aeiouyAEIOUY]";

    public String translate(String english) {
        String[] words = english.trim().split("(\\s|[^0-z])+");

        String prefix = null;
        String stem = null;
        String[] latin = new String[words.length];
        String word = null;

        for (int i=0; i<words.length; i++) {
            word = words[i];
            if (word.length() == 1 || word.matches(VOWELS.concat("*"))) {
                prefix = "";
                stem = word;
            } else {
                prefix = word.split(VOWELS)[0];
                stem = word.substring(prefix.length());
            }

            if (prefix == null) prefix = "";

            if (word.matches(VOWELS.concat("+"))) {
                prefix = prefix.concat("y");
            }

            latin[i] = stem.concat(prefix).concat("ay");
            latin[i] = adjustCase(word, latin[i]);
        }

        return punctuate(english, latin);
    }

    private String adjustCase(String english, String latin) {
        if (english.matches("[0-Z]{2,}")) {
            latin = latin.toUpperCase();
        } else
        if (english.substring(0,1).matches("[A-Z]")) {
            latin = latin.toLowerCase();
            latin = latin.replaceFirst(latin.substring(0,1), latin.substring(0,1).toUpperCase());
        }

        return latin;
    }

    private String punctuate(String english, String[] latin) {
        String[] nonWords = english.trim().split("[0-z]+");

        Integer latinIndex = 0;
        Integer nonWordIndex = 0;

        StringBuilder text = new StringBuilder();

        if (english.substring(0,1).matches("[^0-z]")) {
            text.append(nonWords[nonWordIndex]);
        }

        nonWordIndex++;

        while (latinIndex < latin.length){
            text.append(latin[latinIndex++]);
            if (nonWordIndex < nonWords.length) {
                text.append(nonWords[nonWordIndex++]);
            }
        }

        return text.toString();
    }

}
