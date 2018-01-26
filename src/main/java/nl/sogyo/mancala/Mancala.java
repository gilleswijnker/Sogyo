package nl.sogyo.mancala;

public final class Mancala
{
	private Cube firstCube;
	
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
    
    public Mancala() {
    	firstCube = new Cube();
    }
    
//    public  getCubeInfo(int cubeNumber) {
//    	return firstCube.getCube(cubeNumber);
//    }
}
