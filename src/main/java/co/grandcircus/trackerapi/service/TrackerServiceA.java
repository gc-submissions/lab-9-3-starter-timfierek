package co.grandcircus.trackerapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceA implements TrackerService {

	private List<CountPair> counts;

	public TrackerServiceA() {
		counts = new ArrayList<CountPair>();
	}

	/**
	 * Register a hit for the given token
	 * 
	 * @param token the token to add
	 */
	@Override
	public void add(String token) {
		for (CountPair count : counts) {
			if (count.getToken().equals(token)) {
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
	 * Return the total number of tokens tracked (including duplicates) Tim
	 */
	@Override
	public int getTotalCount() {
		int total = 0;
		for (CountPair count : counts) {
			total += count.getCount();
		}
		return total;
	}

	/**
	 * Return whether the token has been tracked at all
	 * 
	 * @param token the token to check Tim
	 */
	@Override
	public boolean getTokenExists(String token) {
		for (CountPair count : counts) {
			if (count.getToken().equals(token))
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
	 *              Tim
	 */
	@Override
	public int getTokenCount(String token) {
		for (CountPair count : counts) {
			if (count.getToken().equals(token)) {
				return count.getCount();
			}
		}
		return 0;
	}

	/**
	 * Return the most recent token tracked Ryan
	 */
	@Override
	public String getLatest() {

		if (counts.size() == 0) {
			return "No Tokens Tracked";
		}
		String latest = counts.get(counts.size() - 1).getToken();
		return latest;
	}

	/**
	 * Return the token that has been tracked the most times AND how many times it
	 * has been tracked. If there are no tokens return a CountPair("", 0).
	 * 
	 * @return a CountPair including the token and count
	 * 
	 *         Ryan
	 */
	@Override
	public CountPair getTop() {

		if (counts.size() == 0) {
			return new CountPair("", 0);
		}
		CountPair Top = counts.get(0);
		for (CountPair count : counts) {
			if (count.getCount() > Top.getCount()) {
				Top = count;
			}

		}
		return Top;
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

		List<String> latestFive = new ArrayList<>();
		String count;
		// iterate through counts from latest, decrementing until i = counts.size()-5
		if (counts.size() > 5) {
			for (int i = counts.size() - 1; i >= counts.size() - 5; i--) {
				count = counts.get(i).getToken();
				latestFive.add(count);
			}
		} else {
			// iterate through count from latest, decrementing from i until counts.size() = 0?
			// outputs however many exist (<5)
			for (int i = counts.size() - 1; i >= 0; i--) {
				count = counts.get(i).getToken();
				latestFive.add(count);
		}
		}
		return latestFive;
	}

	/**
	 * Return the five tokens that have been tracked the most times AND how many
	 * times they have been tracked. If there are less than five unique tokens,
	 * return CountPairs the available tokens.
	 * 
	 * @return a List of CountPair ordered with the highest count first.
	 * 
	 *         Sam
	 */
	@Override
	public List<CountPair> getTop5() {
		List<CountPair> topFive = new ArrayList<>();
		Collections.sort(counts, topFiveSort);
		// top five
		if (counts.size() > 5) {
			for (int i = 0; i < 5; i++) {
				CountPair count = counts.get(i);
				topFive.add(count);
			}
		// top however many else there are less than 5
		} else {
			for (int i = 0; i < counts.size(); i++) {
				CountPair count = counts.get(i);
				topFive.add(count);
			}
		}
		return topFive;
	}
// comparator for sorting CountPair descending
	public static Comparator<CountPair> topFiveSort = new Comparator<CountPair>() {
		public int compare(CountPair a, CountPair b) {
			int countA = a.getCount();
			int countB = b.getCount();
			return countB-countA;
		}
	};
}
