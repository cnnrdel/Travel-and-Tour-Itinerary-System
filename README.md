# **Travel and Tour Itinerary System**  

## **Project Description**  
The travel itinerary system provides users with an integrated platform to efficiently plan, manage, and book their trips. This system includes booking basic transportation as well as hotels, fetching real-time data via APIs to ensure users have up-to-date information and can book in a timely manner. The system will use the JFrame library. 


## **Features**

### 1. Create and Manage Itineraries
- **Create, View, Edit, and Delete Itineraries**: Users can create new itineraries, view existing ones, edit details to reflect changes in plans, and delete itineraries for past trips.

### 2. View Detailed Itinerary Information
- **Detailed Itinerary Views**: Users can see details of their planned trips, including transportation and hotel bookings.

### 3. Search and Book Hotels
- **Hotel Search**: Users can enter a three-letter abbreviation for a city name to display hotels within that city.
- **Hotel Booking**: Users can select a hotel from the search results to view more details and book a room. The booking is saved locally in a file.

### 4. Search and Book Flights
- **Flight Search**: Users can enter a three-letter abbreviation for a city name for both origin and destination, as well as departure date and number of adults, to display flights related to that location.
- **Flight Booking**: Users can choose from the displayed flights to view more details and book a seat. The booking is saved locally in a file.

### 5. Search and Book Train Tickets
- **Train Search**: Users can enter a three-letter station code and the date and time to display trains related to that location.
- **Train Booking**: Users can select a train from the results to see more details and book a ticket. The booking is saved locally in a file.

### 6. Search and Book Bus Tickets
- **Bus Search**: Users can enter the date to display buses related to that date.
- **Bus Booking**: Users can select a bus to view more details and book a ticket. The booking is saved locally in a file.

## **Installation Instructions**

### 1. Prerequisites
- **Java Development Kit (JDK)**: Ensure you have JDK 22 installed on your system.
- **IntelliJ IDEA**: This project is built using IntelliJ IDEA, so it is recommended to use the same IDE for development.
- **Gson Library**: Download and install Gson from [Maven Repository](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1).

### 2. Setup Instructions
1. **Clone the Repository**: Clone the project repository from your version control system.
2. **Add the Gson Library to Project**:
   - Download the Gson JAR file from the link above.
   - Add the `gson-2.10.1.jar` file to your project's build configuration.
3. **Run the Project**: Open the project in IntelliJ IDEA and run the main class to start the application.

## **How to Use the Project**
1. **Create an Itinerary**: Start by creating a new itinerary from the main menu.
2. **Add Bookings**: Search and book hotels, flights, trains, and buses by navigating to the different sections.
3. **Manage Itineraries**: View, edit, or delete itineraries as needed to keep your plans up to date.
4. **View Detailed Information**: Access detailed information about your bookings and manage your trip efficiently.

## **Credits**
- **Development Team**: Andrew Jiang, Connor DeLeon, Jian Sun
- **External Libraries**: This project uses the Gson library for JSON parsing and manipulation.
