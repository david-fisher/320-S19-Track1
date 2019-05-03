package com.Model2;

import java.util.Comparator;

public class SortByComments implements Comparator<Post> {
	// Used for sorting in ascending order of roll number
	public int compare(Post a, Post b) { return b.comments.size() - a.comments.size(); }
}