package com.company.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientThreadPool {

	private static final ExecutorService thPoolClient = Executors.newFixedThreadPool(20); //Create a pool of threads

	public static void main(String[] args) throws UnknownHostException, IOException {


			thPoolClient.execute(new ClientSocketTask()); //Start a task Thread to build a client request
		}
		//thPoolClient.shutdown(); //shutdown the ThreadPool


	public static String input() throws IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		return console.readLine();
	}

}
