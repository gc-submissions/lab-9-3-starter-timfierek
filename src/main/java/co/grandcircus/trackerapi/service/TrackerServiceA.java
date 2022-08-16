package co.grandcircus.trackerapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceA implements TrackerService{

	private List<CountPair> counts;
	
	public TrackerServiceA() {
		counts = new ArrayList<CountPair>();
	}
	
	/**
	 * Register a hit for the given token
	 * 
	 * @param token
	 *            the token to add
	 */
	@Override
	public void add(String token) {
		for(CountPair count : counts) {
			if(count.getToken().equals(token)) {
				count.setCount(count.getCount() + 1);
				return;
			}
		}
		CountPair newCount = new CountPair(token, 1);
		counts.add(newCount);
	}

	/**
	 * Clear out all records. Reset back to no tokens.
	 */
	@Override
	public void reset() {
		counts = new ArrayList<CountPair>();
	}

	/**
	 * Return the total number of tokens tracked (including duplicates)
	 * Tim
	 */
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Return whether the token has been tracked at all
	 * 
	 * @param token
	 *            the token to check
	 * Tim
	 */
	@Override
	public boolean getTokenExists(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Return the total number of times a given token has been tracked. The
	 * count may be zero.
	 * 
	 * @param token
	 *            the token to check
	 * 
	 * Tim
	 */
	@Override
	public int getTokenCount(String token) {
		// TODO Auto-generated method stub
		return 0;
	}
 
	/**
	 * Return the most recent token tracked
	 * Ryan
	 */
	@Override
	public String getLatest() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the token that has been tracked the most times AND how many times
	 * it has been tracked. If there are no tokens return a CountPair("", 0).
	 * 
	 * @return a CountPair including the token and count
	 * 
	 * Ryan
	 */
	@Override
	public CountPair getTop() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return five the most recent token tracked in order with the most recent
	 * first. The list may contain duplicates if the same token was registered
	 * multiple times. If there are less than five total hits, return all the
	 * available tokens.
	 * 
	 * Sam
	 */
	@Override
	public List<String> getLatest5() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the five tokens that have been tracked the most times AND how many
	 * times they have been tracked. If there are less than five unique tokens,
	 * return CountPairs the available tokens.
	 * 
	 * @return a List of CountPair ordered with the highest count first.
	 * 
	 * Sam
	 */
	@Override
	public List<CountPair> getTop5() {
		// TODO Auto-generated method stub
		return null;
	}

}
