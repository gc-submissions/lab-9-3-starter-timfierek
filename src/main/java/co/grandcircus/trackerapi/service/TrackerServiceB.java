package co.grandcircus.trackerapi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceB implements TrackerService {

	private HashMap<String, CountPair> counts;

	public TrackerServiceB() {
		counts = new HashMap<String, CountPair>();
	}

	/**
	 * Register a hit for the given token
	 * 
	 * @param token the token to add
	 */
	@Override
	public void add(String token) {
		CountPair cur = counts.get(token);
		cur.setCount(cur.getCount() + 1);
	}

	/**
	 * Clear out all records. Reset back to no tokens.
	 */
	@Override
	public void reset() {
		counts = new HashMap<String, CountPair>();
	}

	/**
	 * Return the total number of tokens tracked (including duplicates) Tim
	 */
	@Override
	public int getTotalCount() {
		int total = 0;
		for(String token : counts.keySet()) {
			total += counts.get(token).getCount();
		}
		
		return total;
	}

	/**
	 * Return whether the token has been tracked at all
	 * 
	 * @param token the token to check 
	 * 
	 * Ryan
	 */
	@Override
	public boolean getTokenExists(String token) {
		if (counts.values().equals(token)) {
		return true;
		}
		return false;
		
	}

	/**
	 * Return the total number of times a given token has been tracked. The count
	 * may be zero.
	 * 
	 * @param token the token to check
	 * 
	 * Ryan
	 */
	@Override
	public int getTokenCount(String token) {
		
		return counts.get(token).getCount();
		
		}
	

	/**
	 * Return the most recent token tracked 
	 * 
	 * Sam
	 */
	@Override
	public String getLatest() {
		
	}

	/**
	 * Return the token that has been tracked the most times AND how many times it
	 * has been tracked. If there are no tokens return a CountPair("", 0).
	 * 
	 * @return a CountPair including the token and count
	 * 
	 * Sam    
	 */
	@Override
	public CountPair getTop() {
		
	}

	/**
	 * Return five the most recent token tracked in order with the most recent
	 * first. The list may contain duplicates if the same token was registered
	 * multiple times. If there are less than five total hits, return all the
	 * available tokens.
	 * 
	 * Tim
	 */
	@Override
	public List<String> getLatest5() {
		
	}

	/**
	 * Return the five tokens that have been tracked the most times AND how many
	 * times they have been tracked. If there are less than five unique tokens,
	 * return CountPairs the available tokens.
	 * 
	 * @return a List of CountPair ordered with the highest count first.
	 * 
	 * Tim     
	 */
	@Override
	public List<CountPair> getTop5() {
		
	}

}

