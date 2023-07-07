package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao = new BaseballDAO();
	}
	
	public List<String> readAllNameTeams() {
		return this.dao.readAllNameTeams();
	}
	
	public List<Integer> readAllYears() {
		return this.dao.readAllYears();
	}
	
	
	public void creaGrafo(String nome) {
		
		this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			
		// Aggiunta VERTICI 
		List<Integer> vertici = this.dao.getVertici(nome);
		Graphs.addAllVertices(this.grafo, vertici);
		
		// Aggiunta ARCHI
		for (Integer v1 : vertici) {
			for (Integer v2 : vertici) {
				if(v1<v2) {
			this.grafo.addEdge(v1,v2);
				}
		}
		}
		
		// Aggiunta PESO
				for (Integer v1 : vertici) {
					for (Integer v2 : vertici) {
						if(v1<v2) {
					this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), this.dao.getPeso(nome, v1, v2));
						}
				}
				}
		
		}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Set<Integer> getVertici(){
		
		Set<Integer> vertici=this.grafo.vertexSet();
		
		return vertici;
	}
	
public  List<Arco> getDettagli(int anno) {

		
		Set<Integer> vertici = this.grafo.vertexSet();
	
	List<Arco> result = new ArrayList<Arco>();
		
		
		
				for (Integer v1 : vertici) {
						if(v1!=anno) {
							if((int)this.grafo.getEdgeWeight(this.grafo.getEdge(anno, v1))>0) {
					result.add(new Arco(v1,(int)this.grafo.getEdgeWeight(this.grafo.getEdge(anno, v1))));
							}
						}
				}
				
				Collections.sort(result);
				
				
				return result;
		
		}


}
