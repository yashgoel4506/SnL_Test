package com.qait.automation.SnL_Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Board_Test {
	
	Board board;
	
	@BeforeMethod
	public void startBoard() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}
	
	@Test(expectedExceptions={PlayerExistsException.class})
	public void testToRegisterWithSameName() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		board.registerPlayer("Player 1");
		board.registerPlayer("Player 1");
	}
	
	@Test(expectedExceptions= {MaxPlayersReachedExeption.class})
	public void testMaxPlayer() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		for(int player_no=1; player_no<6; player_no++)
			board.registerPlayer("Player "+player_no);
	}
	
	@Test(expectedExceptions= {GameInProgressException.class})
	public void testToRegisterWhileGameInProgress() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		JSONArray playerArray = board.registerPlayer("Player 1");
		JSONObject playerObj = playerArray.getJSONObject(0);
		String uuid = playerObj.getString("uuid");
		UUID playerUuid = UUID.fromString(uuid);
		board.rollDice(playerUuid);
		board.registerPlayer("Player 2");
	}
	
	@Test(expectedExceptions= {NoUserWithSuchUUIDException.class})
	public void testPlayerUuidExist() throws JSONException, FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException {
		JSONObject playerObj = board.registerPlayer("Player 1").getJSONObject(0);
		String uuid = playerObj.getString("uuid");
		UUID playerUuid = UUID.fromString(uuid);
		board.rollDice(playerUuid);
		board.deletePlayer(playerUuid);
		board.deletePlayer(playerUuid);
	}
	
	@Test(expectedExceptions= {InvalidTurnException.class})
	public void testInvalidTurnOfPlayer() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		JSONObject player1Obj = board.registerPlayer("Player 1").getJSONObject(0);
		String uuid1 = player1Obj.getString("uuid");
		UUID player1Uuid = UUID.fromString(uuid1);
		JSONObject player2Obj = board.registerPlayer("Player 2").getJSONObject(1);
		String uuid2 = player2Obj.getString("uuid");
		UUID player2Uuid = UUID.fromString(uuid2);
		board.rollDice(player2Uuid);
		//board.rollDice(player1Uuid);
	}
	
	

}
