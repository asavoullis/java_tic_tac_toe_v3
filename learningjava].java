public class Main {
  public static void main(String[] args) {
    /* The code below will print the words Hello World
    to the screen, and it is amazing */
    System.out.println("Hello World");  // This is a comment
	String carName = "Volvo";
	int carSpeed = 120;
	int x = 5, y = 6, z = 50;
	System.out.println(x + y + z);
	float myFloatNum = 8.99f;
	char myLetter = 'A';
	boolean myBool = false;
	String myText = "Hello World";
	//byte, short, int, long, float, double, boolean and char are called: primitive data types.
	
	// Type casting - convert the following double type (myDouble) to an int type:
	double myDouble = 9.78d;
	int myInt = (int) myDouble;
	// Use the addition assignment operator to add the value 5 to the variable x.
	int x = 10;
	x += 5; // adds 5 to the value of x and you can use x, you can replace x = x + 5
	++x //increases the value of x by 1
	
	//++x is called preincrement while x++ is called postincrement.
	int x = 5, y = 5;
	System.out.println(++x); // outputs 6
	System.out.println(x); // outputs 6
	System.out.println(y++); // outputs 5
	System.out.println(y); // outputs 6
	
	//print length of function
	String txt = "Hello";
	System.out.println(txt.length());
	System.out.println(txt.toUpperCase()); //uppercase
	
	String firstName = "John ";
	String lastName = "Doe";
	System.out.println(firstName + lastName); //CONCATENATE (add 2 strings in 1 line)
	System.out.println(firstName.concat(lastName));
	
	String txt = "Hello Everybody";
	System.out.println(txt.indexOf("e")); //Return the index (position) of the first occurrence of "e" 

	Math.random(); //Use the correct method to return a random number between 0 (inclusive), and 1 (exclusive).
	
	//if else
	int x = 50;
	int y = 50;
	if (x == y) {
	System.out.println("1");
	} 
	else if (x > y) {
	System.out.println("2");
	} else {
	System.out.println("3");
	} 
	
	// ifelse different case 
	int time = 20;
	String result = (time < 18) ? "Good day." : "Good evening.";System.out.println(result); 
	
	//switch
	switch (day) {
		case 1:
			System.out.println("Saturday");
			break;  
		case 2:
			System.out.println("Sunday");
			break;
		default:
			System.out.println("Weekend");
	}
	
	//while
	int i = 1;
	while(i < 6) {
		System.out.println(i);
		i++;
	}
	
	// do/while
	int i = 1;
	do {
		System.out.println(i);
		i++;
	}
	while (i < 6);
	
	
	for(int i = 0; i < 5; i++) {
		System.out.println("Yes");
	}
	
	//Loop through the items in the cars array.
	String[] cars = {"Volvo", "BMW", "Ford"};
	for (String i : cars) {
		System.out.println(i);
	}
	
	//print 2nd item in the cars array
	String[] cars = {"Volvo", "BMW", "Ford"};
	cars[1] = "Opel"; //Change the value from "Volvo" to "Opel", in the cars array.
	System.out.println(cars[1]);
	System.out.println(cars.length); //array length
	
	
	
  }
}

static void myMethod() {
  System.out.println("I just got executed!");
}

public static void main(String[] args) {
	myMethod()
	myMethod();
}

//Add a fname parameter of type String to myMethod, and output "John Doe".
static void myMethod(String fname) {    //fname is the input, in this case its John
  System.out.println(fname + " Doe");
}

public static void main(String[] args) {
  myMethod("John"); //input johh to method above
}


//Insert the missing part to print the number 8 in main, by using a specific keyword inside myMethod
static int myMethod(int x) {
return 5 + x;
}

public static void main(String[] args) {
  System.out.println(myMethod(3));
}


// Create a checkAge() method with an integer variable called age
static void checkAge(int age) {
// If age is less than 18, print "Access denied"
if (age <18) {
    System.out.println("Access denied"); 
// If age is greater than, or equal to, 18, print "Access granted"
} 
else
 {
    System.out.println("Access granted"); 
  }
} 
public static void main(String[] args) { 
  // Call the checkAge method and pass along an age of 20
checkAge(20);}



//Create an object of MyClass called myObj.
MyClass myObj = new MyClass();

//Use myObj to access and print the value of the x attribute of MyClass.
public class MyClass {
  int x = 5;

  public static void main(String[] args) {
    MyClass myObj = new MyClass();
    System.out.println(myObj.x);
	}
}

public class MyClass {
  public void myMethod() {
    System.out.println("Hello World");
  }

  public static void main(String[] args) {
    MyClass myObj = new MyClass();my
	Obj.myMethod();
  }
}


//The class below should not be inherited by other classes. Add the correct modifier:
final class MyClass

//The Car class should inherit the attributes and methods from the Vehicle class. Add the correct keyword to make this possible.
class Car extends Vehicle

//Camel Case: userLoginCount
//Pascal Case: UserLoginCount
//Snake Case: user_login_count
//Snake Case (All Caps): USER_LOGIN_COUNT
//Kebab Case: user-login-count
