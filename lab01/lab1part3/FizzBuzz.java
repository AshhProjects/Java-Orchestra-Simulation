public class FizzBuzz { //starting the class FizzBuzz

	public static void main(String[] args) { //Main subroutine that it will start in

		for(Integer i = new Integer(1); i < 61; i++){ //for loop, every integer "i" from 1 to 61

				
			if(i % 3 == 0){ //if number modulo 3 is 0 (power of 3) then
				System.out.print("Fizz"); //prints Fizz
			}
				
				
			if(i % 5 == 0){ //if number modulo 5 is 0 (power of 5) then
				System.out.print("Buzz"); //prints Buzz
			}
			
			
			if(i % 3 != 0 && i % 5 != 0){ //if number is not a power of 3 OR 5 then
				System.out.print(i); //prints the number
			}
			
			System.out.println(); //prints empty line
		}
	}
}
