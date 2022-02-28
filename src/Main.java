import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println("  EJEMPLO 1\n");
		Trabajo[] listaTrabajos = new Trabajo[] {
			new Trabajo(1, 4, 1),
			new Trabajo(3, 5, 2),
			new Trabajo(0, 6, 3),
			new Trabajo(4, 7, 4),
			new Trabajo(3, 8, 5),
			new Trabajo(5, 9, 6),
			new Trabajo(6, 10, 7),
			new Trabajo(8, 11, 8)
			};
		System.out.print(
				"\nEl mayor beneficio encontrado \ncompuesto por trabajos no solapados es: " + wisDP(listaTrabajos));

		System.out.println("\n\n  EJEMPLO 2\n");
		Trabajo[] listaTrabajos2 = new Trabajo[] {
			new Trabajo(0, 5, 70),
			new Trabajo(2, 6, 20),
			new Trabajo(1, 3, 60),
			new Trabajo(2, 3, 70),
			new Trabajo(3, 6, 10),
			new Trabajo(5, 7, 40),
			new Trabajo(6, 7, 30),
			new Trabajo(7, 8, 20)
			};
		System.out.print(
				"\nEl mayor beneficio encontrado \ncompuesto por trabajos no solapados es: " + wisDP(listaTrabajos2));

	}

	public static int wisDP(Trabajo[] listaTrabajos) {
		printTrabajos(listaTrabajos);
		if (listaTrabajos.length == 0) return 0;
		
		System.out.print("\nOrdenados por la hora de finalizacion\n");
		Arrays.sort(listaTrabajos);
		printTrabajos(listaTrabajos);

		int n = listaTrabajos.length;
		int[] maxBeneficio = new int[n];
		maxBeneficio[0] = listaTrabajos[0].beneficio;
		for (int i = 1; i < n; i++) {
			int indice = encontrarTrabajoSinConflicto(listaTrabajos, i);
			int incluir = listaTrabajos[i].beneficio;
			if (indice != -1) {
				incluir += maxBeneficio[indice];
			}
			maxBeneficio[i] = Math.max(incluir, maxBeneficio[i - 1]);
		}
		return maxBeneficio[n - 1];
	}

	private static void printTrabajos(Trabajo[] listaTrabajos) {
		System.out.println("Trabajos: ");
		int maxHr = 0;
		int n = 0;
		System.out.print("\t");
		for (Trabajo a : listaTrabajos) {
			if (a.hrFin > 0)
				maxHr = a.hrFin;
		}
		while (n < maxHr + 1) {
			System.out.print(n);
			n++;
		}
		System.out.println();

		for (Trabajo a : listaTrabajos) {
			String s = " ";
			System.out.print("Ben: " + a.beneficio + "\t");
			for (int i = 0; i < maxHr; i++) {
				if (i == a.hrInicio)
					s = "█";
				if (i == a.hrFin)
					s = " ";
				System.out.print(s);
			}
			System.out.println();
		}
	}

	public static int encontrarTrabajoSinConflicto(Trabajo[] listaTrabajos, int n) {
		for (int i = n - 1; i >= 0; i--) {
			if (listaTrabajos[i].hrFin <= listaTrabajos[n].hrInicio) {
				return i;
			}
		}
		return -1;
	}
}
