package com.company.server;
import com.company.User;
import com.company.objects.Team;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.company.server.ServerThreadPool.*;
import static com.company.server.ServerThreadPool.active;







public class AssignTeams implements Runnable {
    public static CopyOnWriteArrayList<Team> teams = new CopyOnWriteArrayList<Team>();

    private static ExecutorService thPoolGame = Executors.newFixedThreadPool(5); //Create a pool of threads

    public static void main() {
        Thread thread = new Thread(new AssignTeams());
        thread.start();
        System.out.println("Exit the main");
    }

    @Override
    public void run() {
        while (true) {
            if(!active.isEmpty() && active.size() /2 == 1){
                CopyOnWriteArrayList<ServerSocketTask> logged = new CopyOnWriteArrayList<ServerSocketTask>(active);
                System.out.println("Active: " + active.size());
                active.clear();
                System.out.println("logged: " + logged.size());
                System.out.println("Teams: " + teams.size());
                Team team = new Team(logged.get(0), logged.get(1),teams.size(),0);
                teams.add(team);
                logged.get(logged.size()-2).setTeamId(teams.size());
                logged.get(logged.size()-2).setTeammate(team.getMember2().getUsername());
                logged.get(logged.size()-2).setCheck(3L);
                logged.get(logged.size()-1).setTeamId(teams.size());
                logged.get(logged.size()-1).setTeammate(team.getMember1().getUsername());
                logged.get(logged.size()-1).setCheck(3L);

                System.out.println("logged: " + logged.size());
                System.out.println("Teams: " + teams.size());

                for (Team value : teams) {
                    System.out.println("team: " + value.getMember1().getUsername() + ", " + value.getMember2().getUsername());

                }

                try {
                    logged.get(logged.size()-2).write("Your teammate is: "+team.getMember2().getUsername() + ", teamID is: " + team.getTeamID(), 3L);
                    logged.get(logged.size()-1).write("Your teammate is: "+team.getMember1().getUsername()+", teamID is: " + team.getTeamID(), 3L);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                thPoolGame.execute(new Game(team.getMember1(),team.getMember2()));




            }

        }
    }


}
