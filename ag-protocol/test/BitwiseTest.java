
public class BitwiseTest {

	public static void main(String[] args) {
		//colocar o valor no primeiro bit do byte
		int first = 0x80; 			// 1000 0000 ->  0x80
		first = 0x40 | first; 		// 0100 0000 ->  0x40
		//
		first = first << 8;     		// 1100 0000 0000 0000
		System.out.println(first);
		//
		int length = 0x03FF;        // 0000 0011 1111 1111 -> 0x03 0xFF -< 1023
		length = length << 4;       // 0011 1111 1111 0000 -> 
		//
		System.out.println(length);
		//
		int result = first | length; // 1100 0000 0000 0000 | 0011 1111 1111 0000 
		//
		System.out.println(result);  // 1111 1111 1111 0000
		
		
				
	}
}
