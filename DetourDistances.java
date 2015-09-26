import java.lang.Math;

public class DetourDistances {

	/**
	 * This method will return the shorter of the distances
	 * of detours of driver 1 and 2.
	 * Because the effective difference between the detour distances
	 * of the two drivers is dist(AB) - dist(CD),
	 * this method will try to calculate the two distances
	 * and return the shorter detour distance.
	 * This method will assume that the detour path of driver one is A, C, D, B,
	 * and that of driver two C, A, B, D.
	 * @param A latitude and longitude for A
	 * @param B latitude and longitude for B
	 * @param C latitude and longitude for C
	 * @param D latitude and longitude for D
	 * @return  the shorter distance of the two detours
	 */
	public static double shorterDistance(double[] A, double[] B, double[] C, double[] D) {
	
		double distAB = distance(A[0], A[1], B[0], B[1]);
		double distCD = distance(C[0], C[1], D[0], D[1]);
		double distAC = distance(A[0], A[1], C[0], C[1]);
		double distBD = distance(B[0], B[1], D[0], D[1]);

		return distAB >= distCD ? distAC + distCD + distBD : distAC + distAB + distBD;
	}
	
	/**
	 * Given two latitude and longitude pairs, calculate the distance between the two.
	 * @param latA latitude of point A
	 * @param longA longitude of point A
	 * @param latB latitude of point B
	 * @param longB longitude of point B
	 * @return the distance between point A and B
	 */
	public static double distance(double latA, double longA, double latB, double longB) {
		double diffLat = Math.toRadians(latB - latA);
		double diffLong = Math.toRadians(longB - longA);
		double radLatA = Math.toRadians(latA);
		double radLatB = Math.toRadians(latB);
		
		double R = 6373000; // radius of the Earth in meters
		double a = Math.pow(Math.sin(diffLat / 2), 2)
		+ Math.cos(radLatA) * Math.cos(radLatB) * Math.pow(Math.sin(diffLong / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}
	
	public static void main(String[] args) {
	
		// for testing purposes
		double[] A = {42.4375, -76.4867};
		double[] B = {42.4442, -76.4786};
		double[] C = {42.4398, -76.4974};
		double[] D = {42.4560, -76.4776};
		
		System.out.printf("%.2fm\n", distance(A[0], A[1], B[0], B[1]));
		System.out.printf("%.2fm\n", shorterDistance(A, B, C, D));
	
	}
	
}