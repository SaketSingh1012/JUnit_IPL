package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int MATCH_SEASON = 1;
    public static final int MATCH_CITY = 2;
    public static final int MATCH_DATE = 3;
    public static final int MATCH_TEAM1 = 4;
    public static final int MATCH_TEAM2 = 5;
    public static final int MATCH_TOSS_WINNER = 6;
    public static final int MATCH_TOSS_DECISION = 7;
    public static final int MATCH_RESULT = 8;
    public static final int MATCH_DL_APPLIED = 9;
    public static final int MATCH_WINNER = 10;
    public static final int MATCH_WIN_BY_RUNS = 11;
    public static final int MATCH_WIN_BY_WICKETS = 12;
    public static final int MATCH_PLAYER_OF_MATCH = 13;
    public static final int MATCH_VENUE = 14;
    public static final int MATCH_UMPIRE1 = 15;
    public static final int MATCH_UMPIRE2 = 16;
    public static final int MATCH_UMPIRE3 = 17;

    public static final int DELIVERY_ID = 0;
    public static final int DELIVERY_INNING = 1;
    public static final int DELIVERY_BATTING_TEAM = 2;
    public static final int DELIVERY_BOWLING_TEAM = 3;
    public static final int DELIVERY_OVER = 4;
    public static final int DELIVERY_BALL = 5;
    public static final int DELIVERY_BATSMAN = 6;
    public static final int DELIVERY_NON_STRIKER = 7;
    public static final int DELIVERY_BOWLER = 8;
    public static final int DELIVERY_SUPER_OVER = 9;
    public static final int DELIVERY_WIDE_RUNS = 10;
    public static final int DELIVERY_BYE_RUNS = 11;
    public static final int DELIVERY_LEG_BYE_RUNS = 12;
    public static final int DELIVERY_NO_BALL_RUNS = 13;
    public static final int DELIVERY_PENALTY_RUNS = 14;
    public static final int DELIVERY_BATSMAN_RUNS = 15;
    public static final int DELIVERY_EXTRA_RUNS = 16;
    public static final int DELIVERY_TOTAL_RUNS = 17;
    public static final int DELIVERY_PLAYER_DISMISSAL = 18;
    public static final int DELIVERY_DISMISSAL_KIND = 19;
    public static final int DELIVERY_FIELDER = 20;

    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveriesData();

        findNumberOfMatchesPlayedPerYear(matches);
        findNumberOfMatchesWonByEachTeam(matches);
        findExtraRunsConcededPerTeamIn2016(matches, deliveries);
        findMostEconomicalBowlersIn2015(matches, deliveries);
    }

    private static List<Delivery> getDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();

        String filePath = "data/deliveries.csv";

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String data;
            bufferedReader.readLine();
            while ((data = bufferedReader.readLine()) != null) {
                String[] fields = data.split(",");

                Delivery delivery = new Delivery();
                delivery.setMatchId(fields[DELIVERY_ID]);
                delivery.setInning(fields[DELIVERY_INNING]);
                delivery.setBattingTeam(fields[DELIVERY_BATTING_TEAM]);
                delivery.setBowlingTeam(fields[DELIVERY_BOWLING_TEAM]);
                delivery.setOver(fields[DELIVERY_OVER]);
                delivery.setBall(fields[DELIVERY_BALL]);
                delivery.setBatsman(fields[DELIVERY_BATSMAN]);
                delivery.setNonStriker(fields[DELIVERY_NON_STRIKER]);
                delivery.setBowler(fields[DELIVERY_BOWLER]);
                delivery.setSuperOver(fields[DELIVERY_SUPER_OVER]);
                delivery.setWideRuns(fields[DELIVERY_WIDE_RUNS]);
                delivery.setLegByeRuns(fields[DELIVERY_LEG_BYE_RUNS]);
                delivery.setByeRuns(fields[DELIVERY_BYE_RUNS]);
                delivery.setNoBallRuns(fields[DELIVERY_NO_BALL_RUNS]);
                delivery.setPenaltyRuns(fields[DELIVERY_PENALTY_RUNS]);
                delivery.setBatsman(fields[DELIVERY_BATSMAN]);
                delivery.setExtraRuns(fields[DELIVERY_EXTRA_RUNS]);
                delivery.setTotalRuns(fields[DELIVERY_TOTAL_RUNS]);
                delivery.setBatsmanRuns(fields[DELIVERY_BATSMAN_RUNS]);

                if (fields.length > 18) {
                    delivery.setPlayerDismissal(fields[DELIVERY_PLAYER_DISMISSAL]);
                    delivery.setDismissalKind(fields[DELIVERY_DISMISSAL_KIND]);
                    if (fields.length > 20) {
                        delivery.setFielder(fields[DELIVERY_FIELDER]);
                    }
                }

                deliveries.add(delivery);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return deliveries;
    }

    private static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();

        String filePath = "data/matches.csv";

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String data;
            bufferedReader.readLine();
            while ((data = bufferedReader.readLine()) != null) {
                String[] fields = data.split(",");

                Match match = new Match();
                match.setId(fields[MATCH_ID]);
                match.setSeason(fields[MATCH_SEASON]);
                match.setCity(fields[MATCH_CITY]);
                match.setDate(fields[MATCH_DATE]);
                match.setTeam1(fields[MATCH_TEAM1]);
                match.setTeam2(fields[MATCH_TEAM2]);
                match.setTossWinner(fields[MATCH_TOSS_WINNER]);
                match.setTossDecision(fields[MATCH_TOSS_DECISION]);
                match.setResult(fields[MATCH_RESULT]);
                match.setDlApplied(fields[MATCH_DL_APPLIED]);
                match.setWinner(fields[MATCH_WINNER]);
                match.setWinByRuns(fields[MATCH_WIN_BY_RUNS]);
                match.setWinByWickets(fields[MATCH_WIN_BY_WICKETS]);
                match.setPlayerOfMatch(fields[MATCH_PLAYER_OF_MATCH]);
                match.setVenue(fields[MATCH_VENUE]);

                if (fields.length > 15) {
                    match.setUmpire1(fields[MATCH_UMPIRE1]);
                    match.setUmpire2(fields[MATCH_UMPIRE2]);
                    if (fields.length > 17) {
                        match.setUmpire3(fields[MATCH_UMPIRE3]);
                    }
                }

                matches.add(match);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return matches;
    }

    public static Map<String, Integer> findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        Map<String, Integer> matchesPlayedPerYear = new HashMap<>();

        for(Match match: matches) {
            matchesPlayedPerYear.put(match.getSeason(),
                    matchesPlayedPerYear.getOrDefault(match.getSeason(), 0) + 1);
        }

        for(Map.Entry<String, Integer> entry: matchesPlayedPerYear.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        return matchesPlayedPerYear;
    }

    public static Map<String, Integer> findNumberOfMatchesWonByEachTeam(List<Match> matches) {
        Map<String, Integer> matchesWonByEachTeam = new HashMap<>();

        for(Match match: matches) {
            if(!match.getWinner().isEmpty()) {
                matchesWonByEachTeam.put(match.getWinner(),
                        matchesWonByEachTeam.getOrDefault(match.getWinner(), 0) + 1);
            }
        }

        for(Map.Entry<String, Integer> entry: matchesWonByEachTeam.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        return matchesWonByEachTeam;
    }

    public static Map<String, Integer> findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        Map<String, Integer> extraRunsConcededPerTeam = new HashMap<>();
        Set<String> matchIds = new HashSet<>();

        for(Match match: matches) {
            if(match.getSeason().equals("2016")) {
                matchIds.add(match.getId());
            }
        }

        for(Delivery delivery: deliveries) {
            if(matchIds.contains(delivery.getMatchId())) {
                extraRunsConcededPerTeam.put(delivery.getBowlingTeam(),
                        extraRunsConcededPerTeam.getOrDefault(delivery.getBowlingTeam(), 0)
                                + Integer.parseInt(delivery.getExtraRuns()));
            }
        }

        for(Map.Entry<String, Integer> entry: extraRunsConcededPerTeam.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        return extraRunsConcededPerTeam;
    }

    public static Map<String, Double> findMostEconomicalBowlersIn2015(List<Match> matches, List<Delivery> deliveries) {
        Map<String, Integer> ballsDeliveredByBowler = new HashMap<>();
        Map<String, Integer> extraRunsConcededByBowler = new HashMap<>();
        Map<String, Double> economyOfBowler = new HashMap<>();
        Set<String> matchIds = new HashSet<>();

        for (Match match : matches) {
            if (match.getSeason().equals("2015")) {
                matchIds.add(match.getId());
            }
        }

        for(Delivery delivery: deliveries) {
            if(matchIds.contains(delivery.getMatchId())) {
                ballsDeliveredByBowler.put(delivery.getBowler(),
                        ballsDeliveredByBowler.getOrDefault(delivery.getBowler(), 0) + 1);

                int legByeRuns = Integer.parseInt(delivery.getLegByeRuns());
                int byeRuns = Integer.parseInt(delivery.getByeRuns());
                int penaltyRuns = Integer.parseInt(delivery.getPenaltyRuns());
                int totalRuns = Integer.parseInt(delivery.getTotalRuns()) - (legByeRuns + byeRuns + penaltyRuns);

                extraRunsConcededByBowler.put(delivery.getBowler(),
                        extraRunsConcededByBowler.getOrDefault(delivery.getBowler(), 0) + totalRuns);
            }
        }

        for(Map.Entry<String, Integer> entry: ballsDeliveredByBowler.entrySet()) {
            double  overs = entry.getValue();
            double economy = ((double) extraRunsConcededByBowler.get(entry.getKey()) / overs)*6;
            String formattedEconomy = String.format("%.2f", economy);
            economyOfBowler.put(entry.getKey(), Double.parseDouble(formattedEconomy));
        }
        System.out.println("Bowler Name: ");
        for (Map.Entry<String, Double> entry : economyOfBowler.entrySet()) {
            System.out.println(entry.getKey() + " Economy: " + entry.getValue());
        }
        return economyOfBowler;
    }
}
