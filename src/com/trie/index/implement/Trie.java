package com.trie.index.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import beanmanager.BeanManager;
import reflection.utility.EntityManager;
import reflection.utility.ManagedEntityManager;

import provider.mysqlConnectorProvider;

import java.util.TreeMap;
import java.util.TreeSet;

import com.trie.index.implement.bean.Dictionary;
import com.trie.index.implement.util.Predicate;

public class Trie {

	
	static Predicate<String> predicate=(t)->{return t!=null?!t.toString().trim().equals(""):false;};
	static class Tree{
	
		String content;
		Tree child[]=new Tree[500];
		
		private Map<Integer,String> possibility=new HashMap<Integer,String>();
		public String getPossibility(Integer key) {
			return possibility.get(key);
		}
		
		public Map<Integer, String> getPossibilityMap() {
			return possibility;
		}
		public void setPossibility(Integer key, String value) {
			possibility.put(key, value);
		}
		boolean isEndofWord;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public boolean isEndofWord() {
			return isEndofWord;
		}
		public void setEndofWord(boolean isEndofWord) {
			this.isEndofWord = isEndofWord;
		}

		
	}
	static TreeSet<String> possibleWords=new TreeSet<String>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        Tree tree =new Tree();
    	BeanManager beanManager = BeanManager.getManagerInstance(mysqlConnectorProvider.class);
    	EntityManager<Dictionary> entityManager = beanManager.getInstance(ManagedEntityManager.class);
    	entityManager.createTable(Dictionary.class);
        
        
		//Map<String, Tree> treeMap = new TreeMap<String, Tree>();
		final String[] values = new String[50000];
		//FileInputStream stream= new FileInputStream(new File("D:\\words.txt"));
		FileReader reader=new FileReader("D:\\words.txt");
		BufferedReader bufferedReader=new BufferedReader(reader);
		int i=0;
		String line=null;
		try {
		while((line=bufferedReader.readLine())!=null && i<50000)
		{
			System.out.println(i+": "+line);
			values[i]=line;
			i++;
		}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {bufferedReader.close(); reader.close();}
		Arrays.stream(values).filter((s)->insertInTree(s,tree)).forEach(System.out::println);
		//System.out.println("search result(team)::::"+searchInTree("team", tree));
		//System.out.println("search result(test)::::"+searchInTree("test", tree));
		Tree tester=tree;
		//tester=tree.child['t'].child['e'];
		//tester=tree.child['e'];
		possibleWords=new TreeSet<String>();
		String word="bol";
		//FileOutputStream oStream= new FileOutputStream(new File("D:\\"+word+".txt"));
		FileWriter writer=new FileWriter("D:\\"+word+".txt");
		BufferedWriter bufferedWriter=new BufferedWriter(writer);		
		iterateRoot(tree,word);
		//getAllPossibilities(tester);
		Dictionary d=new Dictionary();
		Arrays.stream(possibleWords.toArray()).forEach((s)->{ try {
			d.setSearchQuery(word);
			d.setResultQuery((String)s);
			if(predicate.notEmpty(s.toString())) {
			entityManager.persist(d);
			bufferedWriter.write(String.valueOf(s+"##"));
			//System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		});
		System.out.println("i:::::::"+i);	
		try {
		bufferedWriter.flush();
		}
		catch(Exception e)
		{
			
		}finally {
			bufferedWriter.close();
			writer.close();
		}
		
		
		
	}
	
	private static void iterateRoot(Tree tree, String string) {
		// TODO Auto-generated method stub
	 Tree linker;
	linker=tree;
		String[] str=string.split("");
		int i=0;
		for(String s : str)
		{
			if (linker.child[s.charAt(0)]==null)
				break;
			linker=linker.child[s.charAt(0)];
		i++;
		}
		if(i==string.length())
		{
			System.out.println("All pssibilities for string'"+string+"'-->");
			getAllPossibilities(linker);
			
		}
		else
		{
			System.out.println("no matching word found");
			
		}
		
		//Arrays.stream(str).filter((s)-> tree.child[s.charAt(0)]!=null).forEach((s)->{tree=tree.child[s.charAt(0)]; System.out.println(s.charAt(0));});
	}

	private static void getAllPossibilities(Tree tree) {
		//System.out.println(tree);
	
		if(tree!=null && tree.isEndofWord && tree.getPossibilityMap().isEmpty())
		{
			possibleWords.add(tree.content);
		}
		else if(tree!=null && tree.isEndofWord && !tree.getPossibilityMap().isEmpty())
		{
		possibleWords.add(tree.content);
		Iterator<Integer> it = tree.getPossibilityMap().keySet().iterator();
		while(it.hasNext()){
		getAllPossibilities(tree.child[it.next()]);
		}
		//	System.out.println(tree.content);
		}else
		{
			Iterator<Integer> iterator = tree.getPossibilityMap().keySet().iterator();
			while(iterator.hasNext())
			{
				getAllPossibilities(tree.child[iterator.next()]);
			}
			
		}
	}
	private static boolean insertInTree(String s, Tree tree) {
		// TODO Auto-generated method stub
		System.out.println("linker:-"+s);
		Tree linker;
		//System.out.println("root::reference"+tree);
		linker=tree;
		try { 
		for(int i=0,j=i+1;i<s.length();i++,j++)
		{
			if(linker.child[s.charAt(i)]==null)
			{
				linker.child[s.charAt(i)]=new Tree();
			
				if(j<s.length())
				linker.child[s.charAt(i)].possibility.put((int)s.charAt(j), "Y");
				//linker
				
			}
			if(j<s.length())
			linker.child[s.charAt(i)].setPossibility((int)s.charAt(j), "Y");
			linker=linker.child[s.charAt(i)];
			//System.out.println("child object::"+linker);
			//linker.setCharac(s.charAt(i));
			
		}
		linker.isEndofWord=true;
		linker.setContent(s);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}
	
	private static String searchInTree(String s, Tree tree) {
		// TODO Auto-generated method stub
		Tree linker;
		linker=tree;
		try { 
		for(int i=0;i<s.length();i++)
		{
			if(linker.child[s.charAt(i)]!=null)
			{
				linker=linker.child[s.charAt(i)];
				//System.out.println(linker.possibility);
				System.out.println("child object::"+linker);
				continue;
			}
			return "";
			
			
		}
		
		//linker.isEndofWord=true;
		//linker.setContent(s);
		}
		catch(Exception e)
		{
			return "";
		}
		return linker.content;
		
		
	}

}
