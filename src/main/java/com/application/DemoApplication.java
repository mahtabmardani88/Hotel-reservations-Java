package com.application;

import com.application.model.*;
import com.application.repositories.*;
import com.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	ReservationService reservationService;

	@Autowired
	DiscountRepository discountRepository;

	@Autowired
	GuestRepository guestRepository;
	@Autowired
	RoomRepository roomRepository;

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
//

	}

	@Override
	public void run(String... args) throws Exception {

		Guest guest1 = new Guest("Henry", "Extra", "mari12.g1@gmail.com", "0625438674", "0");
		guestRepository.save(guest1);
		Guest guest2 = new Guest("Max", "Level", "mari13.g1@gmail.com", "0627397524", "0");
		guestRepository.save(guest2);
		Guest guest3 = new Guest("Julia", "Best", "mari14.g1@gmail.com", "0697297124", "0");
		guestRepository.save(guest3);
		Guest guest4 = new Guest("Seong", "Lee", "mari15.g1@gmail.com", "0627697534", "0");
		guestRepository.save(guest4);
		Guest guest5 = new Guest("Ali", "Yildiz", "mari16.g1@gmail.com", "0627347124", "0");
		guestRepository.save(guest5);

		Discount discount1 = new Discount(20, LocalDate.now(), LocalDate.of(2024, Month.DECEMBER, 29),"Single");
		discountRepository.save(discount1);
		Discount discount2 = new Discount(12, LocalDate.now(), LocalDate.of(2024, Month.DECEMBER, 1),"Double");
		discountRepository.save(discount2);
		Discount discount3 = new Discount(15, LocalDate.now(), LocalDate.of(2024, Month.DECEMBER, 1),"Penthouse");
		discountRepository.save(discount3);



//		Room room;
//		room = null;
		Room room1 = new Room(1, "first", "single", 2, 1, "tv mini sona", "single", 200.20, true,"Single");
		roomRepository.save(room1);

		Room room2 = new Room(10, "first", "single", 2, 1, "tv mini sona", "double", 200.20, true,"Single");
		roomRepository.save(room2);

		Room room3 = new Room(11, "first", "single", 2, 1, "tv mini sona", "double", 200.20, true,"Single");
		roomRepository.save(room3);

		Room room4 = new Room(12, "first", "single", 2, 1, "tv mini sona", "double", 200.20, true,"Single");
		roomRepository.save(room4);

		Room room5 = new Room(13, "first", "single", 2, 1, "tv mini sona", "double", 200.20, true,"Single");
		roomRepository.save(room5);

		Room room6 = new Room(14, "first", "single", 2, 1, "tv mini sona", "double", 200.20, true,"Single");
		roomRepository.save(room6);


		Reservation reservation;
		reservation = null;
		Reservation reservation1 = new Reservation(LocalDate.of(2023, Month.AUGUST, 2), LocalDate.of(2023, Month.AUGUST, 30),  222.20, false, guest1, room1,discount1);
		reservationService.save(reservation1);
		Reservation reservation2 = new Reservation(LocalDate.of(2023, Month.DECEMBER, 1),LocalDate.of(2023, Month.DECEMBER, 15),  222.20, false, guest2, room2,discount2);
		reservationService.save(reservation2);
		Reservation reservation3 = new Reservation(LocalDate.of(2023, Month.DECEMBER, 4), LocalDate.of(2023, Month.DECEMBER, 6), 222.20, false, guest3, room3,discount3);
		reservationService.save(reservation3);

	}
}