# Paul Bokelman CSA

## CSA Plans

Mainly build the front end and backend application for the smart mirrior. I will also help with putting together the actual hardware of the project in order to get everything working. I will write the frontend of the application in React and deploy on vercel. The backend of the website will be written in node with graphql and will be hosted and served on vercel

## Techtalk notes

All my notes for CSA tech talks

### Techtalk 0

#### Programming Paradigms:

**Imperative Paradigm: **

In computer science, imperative programming is a programming paradigm that uses statements that change a program's state. An imperative program consists of commands for the computer to perform to achieve a result. Imperative programming focuses on describing "how" a program code works.

**Object Oriented Paradigm: **

Object Oriented programming (OOP) is a programming paradigm that relies on the concept of classes and objects. It is used to structure a software program into simple, reusable pieces of code blueprints (usually called classes), which are used to create individual instances of objects.


#### Data structures

**Arrays and lists**

```java
public static Animal[] animalData() {
	return new Animal[]{
	        new Animal("Lion", 8, "Gold"),
	        new Animal("Pig", 3, "Pink"),
		new Animal("Robin", 7, "Red"),
		new Animal("Cat", 10, "Black"),
		new Animal("Kitty", 1, "Calico"),
		new Animal("Dog", 14, "Brown")
	};
}
```

**Hash maps**

```java
private final Map<String, Integer> OPERATORS = new HashMap<>();
{
  OPERATORS.put("*", 3);
  OPERATORS.put("/", 3);
  OPERATORS.put("%", 3);
  OPERATORS.put("+", 4);
  OPERATORS.put("-", 4);
}
```
