package ru.urfu.timus.task35_2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(input.readLine());
        StringBuilder answers = new StringBuilder();
        for (int testIndex = 0; testIndex < testCaseCount; testIndex++) {
            StringTokenizer line = new StringTokenizer(input.readLine());
            int fighterCount = Integer.parseInt(line.nextToken());
            int teamCount = Integer.parseInt(line.nextToken());
            int smallestTeamSize = fighterCount / teamCount;
            int teamsWithOneExtraFighter = fighterCount % teamCount;
            long allUnorderedPairs = (long) fighterCount * (fighterCount - 1) / 2;
            long sameTeamPairs = countPairsWithinBalancedTeams(
                    teamCount, smallestTeamSize, teamsWithOneExtraFighter);
            long crossTeamFightCount = allUnorderedPairs - sameTeamPairs;
            answers.append(crossTeamFightCount).append('\n');
        }
        System.out.print(answers);
    }

    /** Сумма C(size,2) по командам при наиболее равном распределении бойцов. */
    static long countPairsWithinBalancedTeams(int teamCount, int smallestTeamSize, int teamsWithOneExtraFighter) {
        long pairsInLargerTeams = (long) teamsWithOneExtraFighter * smallestTeamSize * (smallestTeamSize + 1) / 2;
        int remainingTeams = teamCount - teamsWithOneExtraFighter;
        long pairsInSmallerTeams = (long) remainingTeams * smallestTeamSize * (smallestTeamSize - 1) / 2;
        return pairsInLargerTeams + pairsInSmallerTeams;
    }
}
