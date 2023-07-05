package it.polito.tdp.exam.model;

import java.util.Objects;

public class Arco implements Comparable<Arco> {
	
	int anno;
	int peso;
	
	
	
	public Arco(int anno, int peso) {
		super();
		this.anno = anno;
		this.peso = peso;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anno, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return anno == other.anno && peso == other.peso;
	}
	@Override
	public int compareTo(Arco o) {
		// TODO Auto-generated method stub
		return (this.peso-o.peso);
	}
	
	

}
