
public class Trabajo implements Comparable<Trabajo> {
	int hrInicio, beneficio;
	Integer hrFin;

	Trabajo(int hrInicio, Integer hrFin, int profit) {
		this.hrInicio = hrInicio;
		this.hrFin = hrFin;
		this.beneficio = profit;
	}
	@Override
	public int compareTo(Trabajo o) {
		if (hrFin > o.hrFin) {
			return 1;
		} else if (hrFin < o.hrFin) {
			return -1;
		}
		return 0;
	}
}
