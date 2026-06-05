package com.saksham.cp_analyzer.service;

import com.saksham.cp_analyzer.dto.ProblemRecommendationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemBankService {

    public List<ProblemRecommendationDTO> getProblemsByTopic(
            String topic
    ) {

        List<ProblemRecommendationDTO> problems =
                new ArrayList<>();

        switch (topic.toLowerCase()) {

            case "arrays":

                problems.add(new ProblemRecommendationDTO(
                        "Two Sum",
                        "Easy",
                        "Arrays",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Contains Duplicate",
                        "Easy",
                        "Arrays",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Best Time To Buy And Sell Stock",
                        "Easy",
                        "Arrays",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Maximum Subarray",
                        "Medium",
                        "Arrays",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Product Of Array Except Self",
                        "Medium",
                        "Arrays",
                        "LeetCode"
                ));

                break;

            case "strings":

                problems.add(new ProblemRecommendationDTO(
                        "Valid Anagram",
                        "Easy",
                        "Strings",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Longest Common Prefix",
                        "Easy",
                        "Strings",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Valid Palindrome",
                        "Easy",
                        "Strings",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Group Anagrams",
                        "Medium",
                        "Strings",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Longest Substring Without Repeating Characters",
                        "Medium",
                        "Strings",
                        "LeetCode"
                ));

                break;

            case "dp":

                problems.add(new ProblemRecommendationDTO(
                        "Climbing Stairs",
                        "Easy",
                        "DP",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Min Cost Climbing Stairs",
                        "Easy",
                        "DP",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "House Robber",
                        "Medium",
                        "DP",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Coin Change",
                        "Medium",
                        "DP",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Longest Increasing Subsequence",
                        "Medium",
                        "DP",
                        "LeetCode"
                ));

                break;

            case "graphs":

                problems.add(new ProblemRecommendationDTO(
                        "Number Of Islands",
                        "Medium",
                        "Graphs",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Clone Graph",
                        "Medium",
                        "Graphs",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Course Schedule",
                        "Medium",
                        "Graphs",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Pacific Atlantic Water Flow",
                        "Medium",
                        "Graphs",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Network Delay Time",
                        "Hard",
                        "Graphs",
                        "LeetCode"
                ));

                break;

            case "binary search":

                problems.add(new ProblemRecommendationDTO(
                        "Binary Search",
                        "Easy",
                        "Binary Search",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Search Insert Position",
                        "Easy",
                        "Binary Search",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Find Peak Element",
                        "Medium",
                        "Binary Search",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Search In Rotated Sorted Array",
                        "Medium",
                        "Binary Search",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Koko Eating Bananas",
                        "Medium",
                        "Binary Search",
                        "LeetCode"
                ));

                break;

            case "linked list":

                problems.add(new ProblemRecommendationDTO(
                        "Reverse Linked List",
                        "Easy",
                        "Linked List",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Middle Of The Linked List",
                        "Easy",
                        "Linked List",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Linked List Cycle",
                        "Easy",
                        "Linked List",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Merge Two Sorted Lists",
                        "Easy",
                        "Linked List",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Remove Nth Node From End",
                        "Medium",
                        "Linked List",
                        "LeetCode"
                ));

                break;

            case "trees":

                problems.add(new ProblemRecommendationDTO(
                        "Maximum Depth Of Binary Tree",
                        "Easy",
                        "Trees",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Invert Binary Tree",
                        "Easy",
                        "Trees",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Same Tree",
                        "Easy",
                        "Trees",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Balanced Binary Tree",
                        "Easy",
                        "Trees",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Binary Tree Level Order Traversal",
                        "Medium",
                        "Trees",
                        "LeetCode"
                ));

                break;

            case "stack":

                problems.add(new ProblemRecommendationDTO(
                        "Valid Parentheses",
                        "Easy",
                        "Stack",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Min Stack",
                        "Medium",
                        "Stack",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Daily Temperatures",
                        "Medium",
                        "Stack",
                        "LeetCode"
                ));

                break;

            case "greedy":

                problems.add(new ProblemRecommendationDTO(
                        "Assign Cookies",
                        "Easy",
                        "Greedy",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Jump Game",
                        "Medium",
                        "Greedy",
                        "LeetCode"
                ));

                problems.add(new ProblemRecommendationDTO(
                        "Gas Station",
                        "Medium",
                        "Greedy",
                        "LeetCode"
                ));

                break;
        }

        return problems;
    }
}
