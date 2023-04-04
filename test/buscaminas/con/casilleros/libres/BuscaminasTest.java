package buscaminas.con.casilleros.libres;

import java.util.Scanner;

public class BuscaminasTest {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Nivel nivel;
		int opcion;
		boolean inicio = true;
		char continuar = 's';
		int fila, columna;
		boolean estaGanando = true;
		int casillerosLiberados =0;
		
		do {
			System.out.println("NIVEL: ");
			System.out.println("*************************");
			System.out.println("1. Facil");
			System.out.println("2. Medio");
			System.out.println("3. Dificil");
			System.out.println("*************************");
			System.out.println("Seleccione un nivel: ");
			opcion = teclado.nextInt();
			
			nivel = Nivel.values()[opcion-1];
			
			Buscaminas buscaminas = new Buscaminas(nivel);
			//------------------------------------------------
			
			
			System.out.println(buscaminas.mostrarTableroFront());
			
			do {
				System.out.println("Ingrese fila:");
				fila = teclado.nextInt();
				
				System.out.println("Ingrese columna");
				columna = teclado.nextInt();
				
				if(inicio == true) {
					buscaminas.generarLosCasillerosConMinas(fila, columna);
					buscaminas.guardarLaCantidadDeBombasQueHayAlrededor(fila, columna);
					
					casillerosLiberados += buscaminas.liberarCasillerosAleatoriamente(fila, columna);
					inicio = false;
				}else {
					if(!buscaminas.consultarHayMinaEn(fila,columna)) {
						if(casillerosLiberados>=nivel.getCantidadMinimaDeCasillerosLiberados())
							buscaminas.guardarLaCantidadDeBombasQueHayAlrededor(fila, columna);
						
						else {
							
							buscaminas.guardarLaCantidadDeBombasQueHayAlrededor(fila, columna);
							casillerosLiberados += buscaminas.liberarCasillerosAleatoriamente(fila, columna);
						}
					}else {
						estaGanando = false;
					}
				}
				
				
				System.out.println(buscaminas.mostrarTableroFront(estaGanando,fila, columna));
				System.out.println(buscaminas.mostrarTableroBack());
				
				
			}while(!buscaminas.consultarHayMinaEn(fila,columna));
			System.out.println("Perdio");
			
			
			
			//-----------FIN -------------------------
			inicio = true;
			System.out.println("¿Desea continuar? (s/n)");
			continuar = teclado.next().toLowerCase().charAt(0);
			
		}while(continuar == 's');
		
		

	}

}
