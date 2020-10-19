package stepDefinition;

import handler.Browser;

public class BaseDef {
	
	public static Browser browser = new Browser();
	/*
	 * Here use STATIC "browser" for all scenarios:
	 * 
	 * Whenever see "I open Browser", the current running browser will be
	 * "disconnected", cannot do any more step on the current browser.
	 * 
	 * ==> Remember to close Browser when a Scenario ends.
	 * 
	 */

}
