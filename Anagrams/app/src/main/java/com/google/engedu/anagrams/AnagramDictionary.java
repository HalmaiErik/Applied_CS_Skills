/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            String key = sortLetters(word);
            wordList.add(word);
            wordSet.add(word);
            if(lettersToWord.containsKey(key))
                lettersToWord.get(key).add(word);
            else {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                lettersToWord.put(key, newList);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word) && !word.contains(base);
    }

    private String sortLetters(String word) {
        char[] tempArr = word.toCharArray();
        Arrays.sort(tempArr);
        return new String(tempArr);
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
       /* for(String word : wordList) {
            if(sortLetters(targetWord).equals(sortLetters(word)))
                result.add(word);
        }
        */
       result = lettersToWord.get(sortLetters(targetWord));
       result.remove(targetWord);
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = getAnagrams(word);
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for(char c : chars) {
            String sortedWord = sortLetters(word + c);
            result.add(sortedWord);
        }
        return result;
    }

    public String pickGoodStarterWord() {
        int randStart = random.nextInt() ;

        // return "stop";
    }
}