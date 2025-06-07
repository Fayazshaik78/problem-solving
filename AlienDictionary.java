package com.Girus.problemsolving;
//Problem 2: Alien Dictionary
//Given a sorted list of words from an alien language, determine the character order used in that language

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {

        String[] words = {"baa", "abcd", "abca", "cab", "cad"};

        String order = findAlienOrder(words);

        System.out.println("✅ Character order in alien language: " + order);
    }

 
    public static String findAlienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>(); 
        Map<Character, Integer> inDegree = new HashMap<>();    

       
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int len = Math.min(word1.length(), word2.length());
            boolean foundDiff = false;

            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                   
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    foundDiff = true;
                    break; 
                }
            }

            if (!foundDiff && word1.length() > word2.length()) {
                return "";
            }
        }

       
        Queue<Character> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

       
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);

            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

    
        if (result.length() != inDegree.size()) {
            return "";
        }

        return result.toString();
    }
}


// sample input String[] words = {"baa", "abcd", "abca", "cab", "cad"};

