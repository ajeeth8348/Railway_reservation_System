import java.util.*;

public class Railway {

        public static void book_ticket(Passenger p) {
                Ticket_booker booker = new Ticket_booker();
                if (Ticket_booker.available_WL <= 0) {
                        System.out.println("No tickets available....................");
                        return;
                } else if (Ticket_booker.available_Lower > 0 && p.req_berth.equals("L")
                                || Ticket_booker.available_middle > 0 && p.req_berth.equals("M")
                                || Ticket_booker.available_upper > 0 && p.req_berth.equals("U")) {
                        System.out.println("Preffered berth is given...........");
                        if (p.req_berth.equals("L")) {
                                booker.bookTicket(p, Ticket_booker.lowerBerth_no.get(0), "L");
                                p.allotted_berth = "L";
                                p.seat_no = Ticket_booker.lowerBerth_no.get(0);

                                Ticket_booker.lowerBerth_no.remove(0);
                                Ticket_booker.available_Lower--;
                        } else if (p.req_berth.equals("M")) {
                                booker.bookTicket(p, Ticket_booker.middleBerth_no.get(0), "M");
                                p.allotted_berth = "M";
                                p.seat_no = Ticket_booker.middleBerth_no.get(0);

                                Ticket_booker.middleBerth_no.remove(0);
                                Ticket_booker.available_middle--;
                        } else if (p.req_berth.equals("U")) {
                                booker.bookTicket(p, Ticket_booker.UpperBerth_no.get(0), "U");
                                p.allotted_berth = "U";
                                p.seat_no = Ticket_booker.UpperBerth_no.get(0);

                                Ticket_booker.UpperBerth_no.remove(0);
                                Ticket_booker.available_upper--;
                        }
                } else if (Ticket_booker.available_Lower > 0) {
                        System.out.println("Lower berth is given............");
                        booker.bookTicket(p, Ticket_booker.lowerBerth_no.get(0), "L");
                        p.allotted_berth = "L";
                        p.seat_no = Ticket_booker.lowerBerth_no.get(0);

                        Ticket_booker.lowerBerth_no.remove(0);
                        Ticket_booker.available_Lower--;
                } else if (Ticket_booker.available_middle > 0) {
                        System.out.println("middle berth  is given...........");
                        booker.bookTicket(p, Ticket_booker.middleBerth_no.get(0), "M");
                        p.allotted_berth = "M";
                        p.seat_no = Ticket_booker.middleBerth_no.get(0);

                        Ticket_booker.middleBerth_no.remove(0);
                        Ticket_booker.available_middle--;

                } else if (Ticket_booker.available_upper > 0) {
                        System.out.println("upper berth is given........");
                        booker.bookTicket(p, Ticket_booker.UpperBerth_no.get(0), "U");

                        Ticket_booker.UpperBerth_no.remove(0);
                        Ticket_booker.available_upper--;

                } else if (Ticket_booker.available_rac > 0) {
                        System.out.println("rac is available.......");
                        booker.bookRac(p, Ticket_booker.rac_no.get(0), "Rac");

                        Ticket_booker.rac_no.remove(0);
                        Ticket_booker.available_rac--;

                } else if (Ticket_booker.available_WL > 0) {
                        System.out.println("WL");
                        booker.bookWL(p, booker.WL_no.get(0), "WL");

                        booker.WL_no.remove(0);
                        Ticket_booker.available_WL--;

                }
        }

        public static void showAvailableTickets() {
                System.out.println("-----------------------------------");
                System.out.println("Available lower berth====" + Ticket_booker.available_Lower);
                System.out.println("Available middle berth====" + Ticket_booker.available_middle);
                System.out.println("Available upper berth====" + Ticket_booker.available_upper);
                System.out.println("Available rac====" + Ticket_booker.available_rac);
                System.out.println("Available wl====" + Ticket_booker.available_WL);
                System.out.println("------------------------------------");
        }

        public static void cancelTicket(int p_id) {
                Ticket_booker booker = new Ticket_booker();
                if (!(Ticket_booker.passengers.containsKey(p_id))) {
                        System.out.println("Unknown passenger id.. Please enter valid id");
                } else
                        booker.cancelBooking(p_id);
        }

        public static void main(String[] args) {
                @SuppressWarnings("resource")
                Scanner sc = new Scanner(System.in);
                boolean bool = true;
                new Ticket_booker();
                new Passenger();

                while (bool) {
                        System.out.println(
                                        "RRS \n 1. Book tickets \n 2. Cancel tickets \n 3. Available ticekts \n 4. Passenger details \n 5. Exit \nEnter the option ");
                        int user_option = sc.nextInt();

                        switch (user_option) {
                                case 1:
                                        System.out.println("Enter passenger details");
                                        System.out.println("Enter name:");
                                        String name = sc.next();
                                        System.out.println("Enter age:");
                                        int age = sc.nextInt();
                                        System.out.println("Prefered berth  L/M/U");
                                        String req_berth = sc.next();
                                        req_berth = req_berth.toUpperCase();

                                        Passenger p = new Passenger(name, age, req_berth);

                                        book_ticket(p);

                                        break;
                                case 2:

                                        System.out.println("Enter passenger id:");
                                        int p_id = sc.nextInt();
                                        cancelTicket(p_id);
                                        break;
                                case 3:
                                        showAvailableTickets();
                                        break;
                                case 4:
                                        Ticket_booker booker = new Ticket_booker();
                                        booker.showPassengers();
                                        break;
                                case 5:
                                        bool = false;
                                        break;
                                default:
                                        System.out.println("Please enter the valid option...........");

                        }
                }

        }

}

class Ticket_booker {
        static int available_Lower = 1;
        static int available_middle = 1;
        static int available_upper = 1;
        static int available_rac = 1;
        static int available_WL = 1;

        static List<Integer> book_tickets_list = new ArrayList<>();
        static Queue<Integer> rac_lists = new LinkedList<>();
        static Queue<Integer> WL_lists = new LinkedList<>();

        static List<Integer> lowerBerth_no = new ArrayList<>(Arrays.asList(1));
        static List<Integer> middleBerth_no = new ArrayList<>(Arrays.asList(1));
        static List<Integer> UpperBerth_no = new ArrayList<>(Arrays.asList(1));
        static List<Integer> rac_no = new ArrayList<>(Arrays.asList(1));
        List<Integer> WL_no = new ArrayList<>(Arrays.asList(1));

        static HashMap<Integer, Passenger> passengers = new HashMap<>();

        public void bookTicket(Passenger p, int seat_no, String allotted_berth) {
                p.allotted_berth = allotted_berth;
                p.seat_no = seat_no;

                passengers.put(p.p_id, p);
                book_tickets_list.add(p.p_id);
                System.out.println("-------------------------------Booked successfully");
                showPassengers();
        }

        public void bookRac(Passenger p, int seat_no, String allotted_berth) {
                p.allotted_berth = allotted_berth;
                p.seat_no = seat_no;

                passengers.put(p.p_id, p);
                rac_lists.add(p.p_id);
                System.out.println("-------------------------added to rac");
        }

        public void bookWL(Passenger p, int seat_no, String allotted_berth) {
                p.allotted_berth = allotted_berth;
                p.seat_no = seat_no;

                passengers.put(p.p_id, p);
                WL_lists.add(p.p_id);
                System.out.println("-------------------------added to WL");
        }

        public void cancelBooking(int p_id) {
                Passenger p = passengers.get(p_id);
                passengers.remove(Integer.valueOf(p_id));
                book_tickets_list.remove(Integer.valueOf(p_id));

                int given_seat_no = p.seat_no;
                if (p.allotted_berth.equals("L")) {
                        available_Lower++;
                        lowerBerth_no.add(given_seat_no);
                } else if (p.allotted_berth.equals("M")) {
                        available_middle++;
                        middleBerth_no.add(given_seat_no);

                } else if (p.allotted_berth.equals("U")) {
                        available_upper++;
                        UpperBerth_no.add(given_seat_no);

                }
                System.out.println("---------Booking cancelled successfully ");

                if (!rac_lists.isEmpty()) {
                        Passenger passengerFromRac = passengers.get(rac_lists.poll());
                        int given_rac_no = passengerFromRac.seat_no;
                        rac_no.add(given_rac_no);

                        rac_lists.remove(Integer.valueOf(passengerFromRac.p_id));
                        available_rac++;
                        if (!WL_lists.isEmpty()) {
                                Passenger passengeFromWL = passengers.get(WL_lists.poll());
                                int given_wl_no = passengeFromWL.seat_no;
                                WL_no.add(given_wl_no);
                                WL_lists.remove(Integer.valueOf(passengeFromWL.p_id));

                                passengeFromWL.seat_no = rac_no.get(0);
                                passengeFromWL.allotted_berth = "Rac";
                                rac_no.remove(0);
                                rac_lists.add(passengeFromWL.p_id);

                                available_WL++;
                                available_rac--;
                        }

                        Railway.book_ticket(passengerFromRac);
                }
        }

        public void showPassengers() {
                if (passengers.isEmpty()) {
                        System.out.println("no passenger details is available");
                        return;
                }
                for (Passenger p1 : passengers.values()) {
                        System.out.println("--------------------------------");
                        System.out.println("passenger id =" + p1.p_id);
                        System.out.println("passenger name=" + p1.name);
                        System.out.println("passenger age=" + p1.age);
                        System.out.println("Allottted berth=" + p1.allotted_berth);
                        System.out.println("Seat number=" + p1.seat_no);
                        System.out.println("--------------------------------");
                }
        }
}

class Passenger {
        static int id = 1;
        String name;
        int age;
        String req_berth;
        int p_id;
        String allotted_berth;
        int seat_no;

        Passenger() {

        }

        Passenger(String name, int age, String req_berth) {
                this.name = name;
                this.age = age;
                this.req_berth = req_berth;
                p_id = id++;
                allotted_berth = "";
                seat_no = 0;
        }

}
