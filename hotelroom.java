import java.util.Scanner;

interface Service {
    double calculateBill(int days, double rate);
}

class Room {
    private final int roomNumber;
    private final String roomType;
    private final double pricePerDay;
    private boolean booked;

    Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.booked = false;
    }

    int getRoomNumber() {
        return roomNumber;
    }

    String getRoomType() {
        return roomType;
    }

    double getPricePerDay() {
        return pricePerDay;
    }

    boolean isBooked() {
        return booked;
    }

    void setBooked(boolean booked) {
        this.booked = booked;
    }
}

class Hotel implements Service {
    private final Room[][] rooms;
    private final int floors;
    private final int roomsPerFloor;

    Hotel(int floors, int roomsPerFloor) {
        this.floors = floors;
        this.roomsPerFloor = roomsPerFloor;
        rooms = new Room[floors][roomsPerFloor];
        initializeRooms();
    }

    private void initializeRooms() {
        for (int floor = 0; floor < floors; floor++) {
            for (int roomIndex = 0; roomIndex < roomsPerFloor; roomIndex++) {
                int roomNumber = (floor + 1) * 100 + roomIndex + 1;
                String roomType;
                double rate;
                switch (floor) {
                    case 0:
                        roomType = "Standard";
                        rate = 100.0;
                        break;
                    case 1:
                        roomType = "Deluxe";
                        rate = 150.0;
                        break;
                    default:
                        roomType = "Suite";
                        rate = 200.0;
                        break;
                }
                rooms[floor][roomIndex] = new Room(roomNumber, roomType, rate);
            }
        }
    }

    void displayRoomStatus() {
        System.out.println("\nRoom Status:");
        for (int floor = 0; floor < floors; floor++) {
            System.out.println("Floor " + (floor + 1) + ":");
            for (int roomIndex = 0; roomIndex < roomsPerFloor; roomIndex++) {
                Room room = rooms[floor][roomIndex];
                String status = room.isBooked() ? "Booked" : "Available";
                System.out.printf("  Room %d (%s, $%.2f): %s%n", room.getRoomNumber(), room.getRoomType(), room.getPricePerDay(), status);
            }
        }
    }

    void bookRoom(int floor, int roomIndex) {
        Room room = getRoom(floor, roomIndex);
        if (room == null) {
            System.out.println("Invalid room selection.");
        } else if (room.isBooked()) {
            System.out.println("Room " + room.getRoomNumber() + " is already booked.");
        } else {
            room.setBooked(true);
            System.out.println("Room " + room.getRoomNumber() + " successfully booked.");
        }
    }

    void cancelBooking(int floor, int roomIndex) {
        Room room = getRoom(floor, roomIndex);
        if (room == null) {
            System.out.println("Invalid room selection.");
        } else if (!room.isBooked()) {
            System.out.println("Room " + room.getRoomNumber() + " is not currently booked.");
        } else {
            room.setBooked(false);
            System.out.println("Booking for room " + room.getRoomNumber() + " has been canceled.");
        }
    }

    Room getRoom(int floor, int roomIndex) {
        if (floor < 0 || floor >= floors || roomIndex < 0 || roomIndex >= roomsPerFloor) {
            return null;
        }
        return rooms[floor][roomIndex];
    }

    @Override
    public double calculateBill(int days, double rate) {
        return days * rate;
    }
}

public class hotelroom {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(3, 3);
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nHotel Room Booking System");
            System.out.println("1. Display room status");
            System.out.println("2. Book room");
            System.out.println("3. Cancel booking");
            System.out.println("4. Calculate bill");
            System.out.println("5. Exit");

            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    hotel.displayRoomStatus();
                    break;

                case 2:
                    int bookFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int bookRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    hotel.bookRoom(bookFloor, bookRoomIndex);
                    break;

                case 3:
                    int cancelFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int cancelRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    hotel.cancelBooking(cancelFloor, cancelRoomIndex);
                    break;

                case 4:
                    int billFloor = readInt(sc, "Enter floor number (1-3): ") - 1;
                    int billRoomIndex = readInt(sc, "Enter room number on floor (1-3): ") - 1;
                    int days = readInt(sc, "Enter number of days: ");
                    Room selectedRoom = hotel.getRoom(billFloor, billRoomIndex);

                    if (selectedRoom == null) {
                        System.out.println("Invalid room selection.");
                    } else if (days <= 0) {
                        System.out.println("Days must be greater than 0.");
                    } else {
                        double bill = hotel.calculateBill(days, selectedRoom.getPricePerDay());
                        System.out.println("Room Number: " + selectedRoom.getRoomNumber());
                        System.out.println("Room Type: " + selectedRoom.getRoomType());
                        System.out.println("Price Per Day: " + selectedRoom.getPricePerDay());
                        System.out.printf("Total Bill: %.2f%n", bill);
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank you for using the hotel booking system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static int readInt(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
            System.out.print(message);
        }
        return sc.nextInt();
    }
}

