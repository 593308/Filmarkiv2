package film;

import adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {

	private int antall;
	private LinearNode<Film> start;

	public Filmarkiv2() {

	}

	@Override
	public Film[] hentFilmTabell() {
		Film[] filmtabell = new Film[antall];

		LinearNode<Film> temp = new LinearNode<>();
		temp = start;
		for (int i = 0; i < antall; i++) {
			filmtabell[i] = temp.getElement();

			if (temp.getNeste() != null)
				temp = temp.getNeste();
		}
		return filmtabell;
	}

	@Override
	public void leggTilFilm(Film nyFilm) {
		LinearNode<Film> film = new LinearNode<>(nyFilm);
		if (antall == 0) {
			start = film;
			antall++;
		} else {
			film.setNeste(start);
			start = film;
			antall++;
		}

	}

	@Override
	public boolean slettFilm(int filmnr) {

		if (start.getElement().getFilmnr() == filmnr) {
			start = start.getNeste();
			antall--;
			return true;
		}

		LinearNode<Film> temp = new LinearNode<>();
		temp = start;

		int count = 1;
		while (temp.getNeste() != null && temp.getNeste().getElement().getFilmnr() != filmnr) {
			temp = temp.getNeste();
			count++;
		}

		if (count == antall - 1) {
			temp.setNeste(null);
			antall--;
			return true;
			
		}
		else if (count < antall - 1) {
			temp.setNeste(temp.getNeste().getNeste());
			antall--;
			return true;
		}
		
		return false;

//		int i = 2;

//		while (i < antall) {
//			if (film.getNeste().getElement().getFilmnr() == filmnr) {
//				film.setNeste(film.getNeste().getNeste());
//				antall--;
//				return true;
//			} else {
//				i++;
//				film = film.getNeste();
//				if (i == antall) {
//					film.setNeste(null);
//					antall--;
//					return true;
//				}
//			}
//		}

//		return false;
	}

	@Override
	public Film[] søkTittel(String delstreng) {

		Filmarkiv2 titler = new Filmarkiv2();
		LinearNode<Film> temp = new LinearNode<>();

		temp = start;

		for (int i = 0; i < antall; i++) {
			if (temp.getElement().getTittel().contains(delstreng)) {
				titler.leggTilFilm(temp.getElement());
			}
			if (temp.getNeste() != null)
				temp = temp.getNeste();
		}
		return titler.hentFilmTabell();
	}

	@Override
	public Film[] søkProdusent(String delstreng) {

		Filmarkiv2 produsenter = new Filmarkiv2();
		LinearNode<Film> temp = new LinearNode<>();

		temp = start;

		for (int i = 0; i < antall; i++) {
			if (temp.getElement().getFilmskaper().contains(delstreng))
				produsenter.leggTilFilm(temp.getElement());
			if (temp.getNeste() != null)
				temp = temp.getNeste();
		}
		return produsenter.hentFilmTabell();
	}

	@Override
	public int antall(Sjanger sjanger) {
		int ant = 0;
		LinearNode<Film> temp = new LinearNode<>();

		temp = start;

		for (int i = 0; i < antall; i++) {
			if (temp.getElement().getSjanger() == sjanger)
				ant++;
			if (temp.getNeste() != null)
				temp = temp.getNeste();
		}
		return ant;
	}

	@Override
	public int antall() {
		return antall;
	}

}
