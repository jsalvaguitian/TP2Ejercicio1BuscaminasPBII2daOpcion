package buscaminas.con.casilleros.libres;
//v1 del casilleros liberados

public class Buscaminas {
	private char[][] tableroFront;
	private boolean[][] tableroBack;
	private Integer filas;
	private Integer columnas;
	private Integer minas;
	private final int MIN_CANT_CASILLEROS_LIBERADOS;
	private int cantidadDeCasillerosLiberados;

	public Buscaminas(Nivel nivel) { // ¿mmm.. Aqui podré arrojar una excepcion para que se encargue el main? :-s
		this.filas = nivel.getFilas();
		this.columnas = nivel.getColumnas();
		this.minas = nivel.getMinas();
		this.tableroBack = new boolean[filas][columnas];
		this.tableroFront = new char[filas][columnas];
		this.cantidadDeCasillerosLiberados = 0;
		this.MIN_CANT_CASILLEROS_LIBERADOS = nivel.getCantidadMinimaDeCasillerosLiberados();
	}

	public String mostrarTableroFront(boolean estaGanando, int fila, int columna) {
		String muestra = "";

		for (int i = 0; i < filas; i++) {
			muestra += i;

			if (i < 10)
				muestra += " ";

			for (int j = 0; j < columnas; j++) {
				if (!estaGanando && this.tableroBack[i][j] == true) {
					if (i == fila && j == columna) {
						this.tableroFront[i][j] = '#';
					} else
						this.tableroFront[i][j] = '*';
				}

				if (this.tableroFront[i][j] == '\0') {
					muestra += "[ ]";
				} else {
					muestra += "[" + tableroFront[i][j] + "]";
				}
			}
			muestra += "\n";
		}
		return muestra;
	}

	public String mostrarTableroFront() {
		String tableroInicial = "";

		for (int i = 0; i < filas; i++) {
			tableroInicial += i;
			if (i < 10)
				tableroInicial += " ";
			for (int j = 0; j < columnas; j++) {
				if (this.tableroFront[i][j] == '\0') {
					tableroInicial += "[ ]";
				}
			}
			tableroInicial += "\n";
		}
		return tableroInicial;
	}

	public String mostrarTableroBack() {
		String muestra = "";

		for (int i = 0; i < filas; i++) {
			muestra += i;
			for (int j = 0; j < columnas; j++) {
				if (this.tableroBack[i][j] == false) {
					muestra += "[ ]";
				} else {
					muestra += "[*]";
				}
			}
			muestra += "\n";
		}

		return muestra;
	}

	public void generarLosCasillerosConMinas(int fila, int columna) {
		int posicionI, posicionJ;
		if (this.validarFilaColumna(fila, columna)) {
			for (int i = 1; i <= this.minas; i++) {
				do {
					posicionI = (int) (Math.random() * this.filas); // 0-7
					posicionJ = (int) (Math.random() * this.columnas);

				} while ((posicionI == fila && posicionJ == columna) || this.tableroBack[posicionI][posicionJ] == true);

				this.tableroBack[posicionI][posicionJ] = true; // en ese casillero ponele la bombita
			}
		}
	}

	private boolean validarFilaColumna(int fila, int columna) {
		if ((fila >= 0 && fila < this.filas) && (columna >= 0 && columna < this.columnas))
			return true;
		else
			return false;
	}

	public void guardarLaCantidadDeBombasQueHayAlrededor(int fila, int columna) {
		int contadorDeMinas = 0;
		if (fila == 0 && columna == 0) {
			for (int i = fila; i <= fila + 1; i++) {
				for (int j = columna; j <= columna + 1; j++) {
					if (this.tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}

		}
		if (fila == this.filas - 1 && columna == 0) {
			for (int i = fila - 1; i <= fila; i++) {
				for (int j = columna; j <= columna + 1; j++) {
					if (this.tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}
		}
		if (fila == 0 && columna == this.columnas - 1) {
			for (int i = fila; i <= fila + 1; i++) {
				for (int j = columna - 1; j <= columna; j++) {
					if (this.tableroBack[i][j] == true)
						contadorDeMinas++;
				}
			}

		}
		if (fila == this.filas - 1 && columna == this.columnas - 1) {
			for (int i = fila - 1; i <= fila; i++) {
				for (int j = columna - 1; j <= columna; j++) {
					if (this.tableroBack[i][j] == true) {
						contadorDeMinas++;
					}

				}

			}
		}
		if (columna == 0 && (fila > 0 && fila < this.filas - 1)) {
			for (int i = fila - 1; i <= fila + 1; i++) {
				for (int j = columna; j <= columna + 1; j++) {
					if (this.tableroBack[i][j] == true)
						contadorDeMinas++;
				}
			}
		}
		if (columna == this.columnas - 1 && (fila > 0 && fila < this.filas - 1)) {
			for (int i = fila - 1; i <= fila + 1; i++) {
				for (int j = columna - 1; j <= columna; j++) {
					if (this.tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}
		}
		if (fila == 0 && (columna > 0 && columna < this.columnas - 1)) {
			for (int i = fila; i <= fila + 1; i++) {
				for (int j = columna - 1; j <= columna + 1; j++) {
					if (tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}
		}
		if (fila == this.filas - 1 && (columna > 0 && columna < this.columnas - 1)) {
			for (int i = fila - 1; i <= fila; i++) {
				for (int j = columna - 1; j <= columna + 1; j++) {
					if (tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}
		}
		if ((fila > 0 && fila < this.filas - 1) && (columna > 0 && columna < this.columnas - 1)) {
			for (int i = fila - 1; i <= fila + 1; i++) {
				for (int j = columna - 1; j <= columna + 1; j++) {
					if (tableroBack[i][j] == true) {
						contadorDeMinas++;
					}
				}
			}
		}

		this.tableroFront[fila][columna] = (char) (contadorDeMinas + '0');

	}

	public boolean consultarHayMinaEn(int fila, int columna) {
		if (this.tableroBack[fila][columna] == true) {
			return true;
		}
		return false;
	}

	/*
	 * Retorna cantidadDeCasillerosliberados
	 */
	public int liberarCasillerosAleatoriamente(int fila, int columna) { // bueno no tan asi... que sean consecutivos a
																		// la ubicación del jugador (lo más parecido al
																		// juego)
		int filasALiberar = (int) (Math.random() * (this.filas - 3 + 1) + 3); // 2 filas o hasta el length
		int limiteSegunLaUbicacionI = this.filas - fila;

		System.out.println("FILAS MOSTRAR " + filasALiberar);
		System.out.println("limiteDeFilas: " + limiteSegunLaUbicacionI);

		// 4 <= 4
		if (filasALiberar <= limiteSegunLaUbicacionI) {
			for (int i = fila; i < filasALiberar; i++) {
				if (columna < this.columnas - 1) {
					for (int j = columna; j < this.columnas; j++) {
						if (this.tableroBack[i][j] == true) { // hay una mina en el casillero?
							if (j > 0) {
								guardarLaCantidadDeBombasQueHayAlrededor(i, j - 1);
								this.cantidadDeCasillerosLiberados++;
								break; //
							} else {
								guardarLaCantidadDeBombasQueHayAlrededor(i, j + 1);
								this.cantidadDeCasillerosLiberados++;
								break; //

							}

						} else if (this.tableroBack[i][j] != true) {
							guardarLaCantidadDeBombasQueHayAlrededor(i, j);
							this.cantidadDeCasillerosLiberados++;
						}
					}
				}
				if (columna == this.columnas - 1) {
					for (int j = columna; j <= this.columnas; j--) {
						if(j>0) {
							if (this.tableroBack[i][j - 1] == true) { // hay bomba a lado?
								// if(j>0) {
								guardarLaCantidadDeBombasQueHayAlrededor(i, j);
								this.cantidadDeCasillerosLiberados++;
								break;
								// }
							} else {

								guardarLaCantidadDeBombasQueHayAlrededor(i, j);
								this.cantidadDeCasillerosLiberados++;

							}
							
						}
						
					}
				}

			}
		} else { // 8-3= 5; 5
			for (int i = this.filas - filasALiberar; i < this.filas - 1; i++) {
				// extracted(posicionJ, i);
				if (columna < this.columnas - 1 && columna > 0) {
					for (int j = columna; j < this.columnas; j++) {
						if (this.tableroBack[i][j] == true) { // hay una mina en el casillero siguiente?
							guardarLaCantidadDeBombasQueHayAlrededor(i, j - 1);
							this.cantidadDeCasillerosLiberados++;
							break; //
						} else if (this.tableroBack[i][j] != true) {
							guardarLaCantidadDeBombasQueHayAlrededor(i, j);
							this.cantidadDeCasillerosLiberados++;
						}
					}
				}
				if (columna == this.columnas - 1) {
					for (int j = columna; j <= this.columnas; j--) {
						if (j > 0) {
							if (this.tableroBack[i][j] == true) { // hay bomba aqui?

								//if (j > 0) {
									guardarLaCantidadDeBombasQueHayAlrededor(i, j - 1);
									this.cantidadDeCasillerosLiberados++;
									break; //
								//}

							} else {
								guardarLaCantidadDeBombasQueHayAlrededor(i, j);
								this.cantidadDeCasillerosLiberados++;

							}

						}

					}
				}

			}
		}
		return cantidadDeCasillerosLiberados;
	}

}
