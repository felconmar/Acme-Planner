package acme.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.features.administrator.spam.AdministratorSpamRepository;


public class SpamComponent {
	
	@Autowired
	static AdministratorSpamRepository repository;

	
	public static List<List<String>> getComposedSpamWords(final List<String>spamWords){
		final List<List<String>>res = new ArrayList<List<String>>();
		
		for (final String word : spamWords) {
			final String[]aux = word.split(" ");
			
			final List<String> composedSpamWords = new ArrayList<String>();
			if(aux.length>1) {
				for (final String w : aux) {
					if(w.length()>0) {
						composedSpamWords.add(w);
					}
				}
				res.add(composedSpamWords);
			}
		}
		
		return res;
	}
	
	public static List<String> formatWordsInput(final String text){
		
		final String[]words = text.toLowerCase().split(" ");
		final List<String>wordsInput = new ArrayList<>();
		
		for (final String p : words) {
			if(p.length()>0) {
				wordsInput.add(p);
			}
		}
		return wordsInput;
	}
	
	public static boolean containSpam(final String text, final List<String>spamWords) {
		
		final List<List<String>> composedSpamWords = SpamComponent.getComposedSpamWords(spamWords);
		final List<String> wordsInput= SpamComponent.formatWordsInput(text);
		
		final boolean res = false;
		int spamCounter = 0;
		int totalWordsCounter=0;

		for ( int i=0; i<wordsInput.size(); ) { //Comprobamos si se trata de spam simple
			final String w = wordsInput.get(i);
			if(spamWords.contains(w)) {
				spamCounter++;
				totalWordsCounter++;
				i++;
			}else{ //Comprobamos si se trata de una palabra de spam compuesta por varias palabras
				
				boolean coincide=false;
				 int numIndexAdd=1;
				for(int j=0; j<composedSpamWords.size(); j++) {
					
					final List<String>composedSpamWord = composedSpamWords.get(j);
					if(!coincide && wordsInput.size()-i >= composedSpamWord.size()) {
						 coincide = SpamComponent.containSpamRecursive(wordsInput,composedSpamWord,i,0);
						 if(coincide) {
							 numIndexAdd=composedSpamWord.size();
							 break;
						 	}
						}
					
					}
				if(coincide) {
					 spamCounter++;
					}
				i+=numIndexAdd;
				totalWordsCounter++;
				}
			}
		
		return SpamComponent.exceedsThreshold(10.0, spamCounter, totalWordsCounter);
	
		}
	
	public static final  boolean exceedsThreshold(final Double threshold,  final int spamCounter, final int totalWordsCounter) {

		boolean res=false;
		final Double spamThreshold = 10.0;
		final Double percentageSpam = ((double)spamCounter/totalWordsCounter)*100;
		
		if(percentageSpam>=spamThreshold) res = true;
		return res;
		
	}
		
	public static boolean containSpamRecursive(final List<String>wordsInput,final List<String>composedSpamWord, final int i, final int c) {
		
		
		final String inputWord= wordsInput.get(i);
		final String composedWord= composedSpamWord.get(c);
		
	if(c<composedSpamWord.size()-1) {
	
		if(inputWord.equals(composedWord)) {
			return SpamComponent.containSpamRecursive(wordsInput,composedSpamWord,i+1,c+1);
		}else {
			return false;
		}
		 
	}else {
		return inputWord.equals(composedWord);
		}
		
	}
}
