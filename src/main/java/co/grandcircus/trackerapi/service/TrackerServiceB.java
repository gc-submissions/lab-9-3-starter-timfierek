package co.grandcircus.trackerapi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceB implements TrackerService {

	private LinkedHashMap<String, CountPair> counts;

	public TrackerServiceB() {
		counts = new LinkedHashMap<String, CountPair>();
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
		counts.clear();
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
		if (counts.keySet().contains(token)) {
		return true;
		}
//		return(counts.containsKey(token));
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
		for(CountPair count : counts.values()) {
			if(count.getToken().equals(token)) {
				return counts.get(token).getCount();
			}
		}
		
			return 0;
	}
		
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
		List<String> latest5 = new ArrayList<String>();
		LinkedList<String> allTokens = new LinkedList<String>();
		for(String key : counts.keySet()) {
			allTokens.addFirst(key);
		}
		
		int index = 0;
		for(String token : allTokens) {
			if(index == 5) {
				return latest5;
			}
			latest5.add(token);
			index++;
		}
		
		
		return latest5;
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
		ArrayList<CountPair> countPairs = new ArrayList<CountPair>();
		
		for(String token : counts.keySet()) {
			countPairs.add(counts.get(token));
		}
		
		countPairs.sort(topFiveSort);
		List<CountPair> result = new ArrayList<CountPair>();
		
		int index = 0;
		for(CountPair c : countPairs) {
			if(index == 5) {
				return result;
			}
			result.add(index, c);
			index++;
		}
		
		return result;
	}

	public static Comparator<CountPair> topFiveSort = new Comparator<CountPair>() {
		public int compare(CountPair a, CountPair b) {
			int countA = a.getCount();
			int countB = b.getCount();
			return countB-countA;
		}
	};
}

