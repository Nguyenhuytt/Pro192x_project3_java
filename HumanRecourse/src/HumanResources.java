import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
Lớp HumanResources thực hiện chương trình quản lý nhân sự.
*/
public class HumanResources {
	// ArrayList departments lưu các bộ phận của công ty
	public static ArrayList<Department> departments = new ArrayList<>();

	// ArrayList titles lưu các chức danh của quản lý
	public static ArrayList<String> titles = new ArrayList<>();

	// ArrayList staffs lưu tất cả nhân viên của công ty
	public static ArrayList<Staff> staffs = new ArrayList<>();

	// Phương thức chạy luồng chính của chương trình
	public static void main(String[] args) {
		// Gọi 2 phương thức khởi tạo các bộ phận, chức danh
		initiateDepartment();
		initiateTitle();
		// Gọi khởi tạo sẵn 11 nhân viên, comment dòng dưới nếu muốn bắt đầu từ 0 nhân
		// viên
		initiateStaff();
		Scanner console = new Scanner(System.in);
		boolean stop = false;
		String control;
		do {
			control = chooseFunction(console);
			switch (control) {
			case "0":
				stop = true;
				displayEscape(); // Phương thức báo dừng trương trình
				break;
			case "1":
				displayAllStaff(); // Phương thức hiển thị toàn bộ nhân viên
				break;
			case "2":
				displayAllDepartment(); // Phương thức hiển thị toàn bộ phận
				break;
			case "3":
				displayStaffOfDepartment(); // Phương thức hiển thị nhân viên từng bộ phận
				break;
			case "4":
				importStaff(console); // Phương thức thêm nhân viên mới
				break;
			case "5":
				searchStaff(console); // Phương thức tìm kiếm nhân viên
				break;
			case "6":
				displayStaffSalary(); // Phương thức hiển thị lương toàn nhân viên
				break;
			case "7":
				displayStaffSortBySalary(console); // Phương thức xắp xếp theo thứ tự lương
				break;
			default:
				displayError(control); // Phương thức báo lỗi thi số nhập vào không hợp lệ
				break;
			}
		} while (!stop);
	}

	// Phương thức khởi tạo các bộ phận của công ty
	public static void initiateDepartment() {
		departments.add(new Department(0, "PHÒNG IT"));
		departments.add(new Department(1, "PHÒNG SEO"));
		departments.add(new Department(2, "PHÒNG MARKETING"));
	}

	// Phương thức khởi tạo các chức danh của quản lý
	public static void initiateTitle() {
		titles.add("Business Leader");
		titles.add("Project Leader");
		titles.add("Technical Leader");
	}

	// Phương thức khởi tạo 11 nhân viên
	public static void initiateStaff() {
		staffs.add(new Employee(0, "NGUYỄN QUANG HUY", 25, departments.get(2), "20/01/2009", 2, 11, 2));
		staffs.add(new Employee(1, "PHẠM QUỲNH ANH", 22, departments.get(0), "20/01/2021", 2, 3, 2));
		staffs.add(new Employee(2, "TRƯƠNG THẾ VINH", 25, departments.get(2), "20/01/2019", 10, 6, 2));
		staffs.add(new Employee(3, "ĐÀO LAN HƯƠNG", 26, departments.get(2), "01/03/2019", 11, 6, 2));
		staffs.add(new Employee(4, "NGÔ BẢO CHÂU", 21, departments.get(0), "01/08/2021", 1, 2, 2));
		staffs.add(new Employee(5, "DƯƠNG NGỌC ÁNH", 23, departments.get(1), "01/08/2020", 2, 2, 2));
		staffs.add(new Employee(6, "KHỔNG THỊ DƯƠNG", 20, departments.get(1), "23/11/2015", 12, 0, 2));
		staffs.add(new Manager(7, "NGÔ THANH TÙNG", 28, departments.get(0), "Business Leader", "20/01/2019", 10, 3));
		staffs.add(new Manager(8, "CAO THÁI SƠN", 29, departments.get(1), "Technical Leader", "01/01/2010", 2, 3));
		staffs.add(new Manager(9, "NGUYỄN ĐÌNH THI", 32, departments.get(2), "Project Leader", "31/01/2021", 3, 3));

	}

	// Phương thức hiển thị menu tổng và lấy yêu cầu từ người dùng
	public static String chooseFunction(Scanner console) {
		String control;
		System.out.println("MENU TỔNG - LỰA CHỌN CHỨC NĂNG");
		System.out.println("Phím 1. Hiển thị danh sách nhân viên hiện có trong công ty.");
		System.out.println("Phím 2. Hiển thị các bộ phận trong công ty.");
		System.out.println("Phím 3. Hiển thị các nhân viên theo từng bộ phận.");
		System.out.println("Phím 4. Thêm nhân viên mới vào công ty.");
		System.out.println("Phím 5. Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
		System.out.println("Phím 6. Hiển thị bảng lương của nhân viên toàn công ty.");
		System.out.println("Phím 7. Hiển thị danh sách nhân viên tăng / giảm dần theo lương.");
		System.out.println("Phím 0. Kết thúc chương trình.");
		System.out.print("-> Chọn chức năng của bạn: ");
		control = console.nextLine().trim();
		System.out.println();
		return control;
	}

	// Phương thức hiển thị lời chào khi người dùng chọn kết thúc chương trình
	public static void displayEscape() {
		System.out.println("Xin chào và hẹn gặp lại.");
		System.out.println("CHƯƠNG TRÌNH KẾT THÚC!");
	}

	// Phương thức hiển thị thông tin của tất cả nhân viên
	public static void displayAllStaff() {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu hiển thị toàn bộ nhân viên (Chức năng 1).");
		if (staffs.size() == 0) {
			System.out.println("Hiện tại công ty không có nhân viên nào.");
			System.out.println("Không thể hiển thị toàn bộ nhân viên.");
			System.out.println();
			return;
		}
		showTable();
		// Dùng vòng lặp for each để hiển thị lần lượt toàn bộ nhân viên
		for (Staff staff : staffs) {
			staff.displayInformation();
		}
		System.out.println(
				"|___|____________________|_____|__________________|_________________|_____________|_____________|_____________|___________|_________________|");

	}

	// show table
	static void showTable() {
		System.out.println(
				"|-------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println(String.format("|%3s|%20s|%5s|%18s|%17s|%13s|%13s|%13s|%11s|%13s VND|", "ID", "TÊN NHÂN VIÊN",
				"TUỔI", "BỘ PHẬN", "CHỨC DANH", "NGÀY VÀO LÀM", "SỐ NGÀY NGHỈ", "GIỜ LÀM THÊM", "HỆ SỐ LƯƠNG",
				"MỨC LƯƠNG"));
		System.out.println(
				"|-------------------------------------------------------------------------------------------------------------------------------------------|");
	}

	// Phương thức hiển thị thông tin của tất cả bộ phận
	public static void displayAllDepartment() {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu hiển thị danh sách bộ nhận (Chức năng 2).\n");
		System.out.println("|----------------------------------------|");
		System.out.println(String.format("|%5s|%20s|%13s|", "ID", "TÊN BỘ PHẬN", "SỐ NHÂN VIÊN"));
		System.out.println("|----------------------------------------|");
		//Dùng vòng lặp for each để duyệt từng bộ phân 
		for (Department department : departments) {
			for (Staff staff : staffs) {
				//Nếu tên bộ phận trong Arraylist<staff> trùng với tên từng bộ phận thì tăng số nhân viên trong bộ phận đó lên 1
				if (staff.getDepartment().getDepartmentName() == department.getDepartmentName()) {
					department.plusStaffCount();
				}
			}
			System.out.println(department);//Hiển thị thông tin tất cả các bộ phận

		}

		System.out.println("|_____|____________________|_____________|");
		/*
		 * Vòng lặp for dưới để loại bỏ số nhân viên từng bộ phận vừa được thêm ở trên
		 * để khi gọi lại chức năng 2 sẽ không bị lặp lại 2 lần số lượng nhân viên
		 */
		for (Department department : departments) {

			for (Staff staff : staffs) {
				if (staff.getDepartment().getDepartmentName() == department.getDepartmentName()) {
					department.subtractStaffCount();
				}
			}

		}

	}

	// Phương thức hiển thị thông tin của nhân viên theo từng bộ phận
	public static void displayStaffOfDepartment() {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu hiển thị nhân viên theo bộ phận (Chức năng 3).\n");
		//Nếu độ dài của Arraylist<staff>==0 thì báo không có nhân viên 
		if (staffs.size() == 0) {
			System.out.println("Hiện tại công ty không có nhân viên nào.");
			System.out.println("Không thể hiển thị nhân viên theo bộ phận.");
			System.out.println();
			return;
		}
		//Dùng vòng lặp for each để duyệt từng bộ phận
		for (Department department : departments) {
			System.out.println(department.getDepartmentName());
			System.out.println();
			showTable();
			//Dùng for each để duyệt và hiển thị nhân viên trong từng bộ phận
			for (Staff staff : staffs) {
				if (staff.getDepartment().getDepartmentId() == department.getDepartmentId()) {
					staff.displayInformation();
				}
			}
			System.out.println(
					"|___|____________________|_____|__________________|_________________|_____________|_____________|_____________|___________|_________________|");

			System.out.println();
		}
	}

	// Phương thức thêm nhân viên mới
	public static void importStaff(Scanner console) {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu nhập nhân viên (Chức năng 4).");
		System.out.println("Phím 1. Nhập nhân viên thông thường.");
		System.out.println("Phím 2. Nhập nhân viên cấp quản lý.");
		System.out.println("Phím 0. Thoát chức năng.");
		System.out.print("-> Chọn chức năng của bạn: ");
		String control = console.nextLine().trim();
		System.out.println();
		switch (control) {
		case "0":
			return;
		case "1":
			importEmployee(console); //Phương thức thêm nhân viên thông thường
			break;
		case "2":
			importManager(console); //Phương thức thêm quản lý
			break;
		default:
			displayError(control); //Phương thức báo lỗi khi nhập thông tin sai
			break;
		}
	}

	// Phương thức thêm nhân viên thông thường
	public static void importEmployee(Scanner console) {
		System.out.println("Nhập thông tin nhân viên thông thường.");
		int id = importId(console); //Phương thức nhập id
		String name = importName(console); //Phương thức nhập tên 
		int age = importAge(console); //Phương thức nhập tuổi
		int factorOfSalary = importFactorOfSalary(console); //Phương thức nhập hệ số lương
		String startDate = importStaffDate(console); //Phương thức nhập ngày vào làm
		Department department = importDepartment(console); //Phương thức nhập bộ phận
		int dayOff = importDayOff(console); //Phương thức nhập ngày nghỉ
		int overTime = importOverTime(console); //Phương thức nhập thời gian làm thêm
		Staff staff = new Employee(id, name, age, department, startDate, dayOff, overTime, factorOfSalary);
		staffs.add(staff);
		System.out.println("Đã nhập xong, hiển thị thông tin nhân viên mới nhập.");
		staff.displayInformation(); //Hiển thị thông tin nhân viên vừa nhập
	}

	// Phương thức thêm nhân viên cấp quản lý
	public static void importManager(Scanner console) {
		System.out.println("Nhập thông tin nhân viên cấp quản lý.");
		int id = importId(console); //Phương thức nhập id
		String name = importName(console); //Phương thức nhập tên 
		int age = importAge(console); //Phương thức nhập tuổi
		int factorOfSalary = importFactorOfSalary(console); //Phương thức nhập hệ số lương
		String startDate = importStaffDate(console); //Phương thức nhập ngày vào làm
		Department department = importDepartment(console); //Phương thức nhập bộ phận
		int dayOff = importDayOff(console); //Phương thức nhập ngày nghỉ
		String title = importTitle(console); //Phương thức nhập chức danh
		Staff staff = new Manager(id, name, age, department, title, startDate, dayOff, factorOfSalary);
		staffs.add(staff);
		System.out.println("Đã nhập xong, hiển thị thông tin nhân viên mới nhập.");
		staff.displayInformation(); //Hiển thị quản lý vừa nhập
	}

	// Phương thức nhập mã nhân viên mới
	public static int importId(Scanner console) {
		System.out.print("Nhập mã nhân viên: ");
		int id = console.nextInt();
		console.nextLine();
		return id;
	}

	// Phương thức nhập tên nhân viên mới
	public static String importName(Scanner console) {
		System.out.print("Nhập tên nhân viên: ");
		return console.nextLine();
	}

	// Phương thức nhập tuổi nhân viên
	public static int importAge(Scanner console) {
		System.out.print("Nhập tuổi nhân viên: ");
		int age = console.nextInt();
		return age;
	}

	// Phương thức nhập hệ số lương của nhân viên mới
	public static int importFactorOfSalary(Scanner console) {
		System.out.print("Nhập hệ số lương: ");
		int factorOfSalary = console.nextInt();
		console.nextLine();
		return factorOfSalary;
	}

	// Phương thức nhập ngày làm việc của nhân viên mới
	public static String importStaffDate(Scanner console) {
		System.out.print("Nhập ngày bắt đầu làm việc (dd/mm/yyyy): ");
		String startDate = console.nextLine();
		return startDate;
	}

	// Phương thức nhập bộ phận làm việc của nhân viên mới
	public static Department importDepartment(Scanner console) {
		System.out.println("Nhập bộ phận làm việc: ");
		System.out.println("-) Phím 0 để chọn bộ phận phòng it.");
		System.out.println("-) Phím 1 để chọn bộ phận phòng seo.");
		System.out.println("-) Phím 2 để chọn bộ phận phòng marketing.");
		System.out.print("-> Chọn bộ phận: ");
		int departmentId = console.nextInt();
		return departments.get(departmentId);

	}

	// Phương thức nhập số ngày nghỉ của nhân viên mới
	public static int importDayOff(Scanner console) {
		System.out.print("Nhập số ngày nghỉ: ");
		int dayOff = console.nextInt();
		return dayOff;
	}

	// Phương thức nhập số giờ làm thêm của nhân viên mới
	public static int importOverTime(Scanner console) {
		System.out.print("Nhập giờ làm thêm: ");
		int overTime = console.nextInt();
		console.nextLine();
		return overTime;
	}

	// Phương thức nhập chức danh của nhân viên mới
	public static String importTitle(Scanner console) {
		System.out.println("Nhập chức danh: ");
		System.out.println("-) Phím 0 để chọn chức danh Business Leader.");
		System.out.println("-) Phím 1 để chọn chức danh Project Leader.");
		System.out.println("-) Phím 2 để chọn chức danh Technical Leader.");
		System.out.print("-> Chọn chức danh: ");
		int titleIndex = console.nextInt();
		console.nextLine();
		return titles.get(titleIndex);
	}

	// Phương thức tìm kiếm nhân viên theo mã hoặc tên
	public static void searchStaff(Scanner console) {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu tìm kiếm nhân viên (Chức năng 5).");
		if (staffs.size() == 0) {
			System.out.println("Hiện tại công ty không có nhân viên nào.");
			System.out.println("Không thể tìm kiếm nhân viên.");
			System.out.println();
			return;
		}
		System.out.println("Phím 1. Tìm kiếm theo mã nhân viên.");
		System.out.println("Phím 2. Tìm kiếm theo tên nhân viên.");
		System.out.println("Phím 0. Thoát chức năng.");
		System.out.print("-> Chọn chức năng của bạn: ");
		String control = console.nextLine().trim();
		System.out.println();
		switch (control) {
		case "0":
			return;
		case "1":
			searchStaffById(console); //Phương thức tìm kiếm nhân viên theo id
			break;
		case "2":
			searchStaffByName(console); //Phương thức tìm kiếm nhân viên theo tên
			break;
		default:
			displayError(control); //Phương thức báo lối khi nhập thông tin sai
			break;
		}
	}

	// Phương thức tìm kiếm nhân viên bằng mã
	public static void searchStaffById(Scanner console) {
		System.out.print("Bạn vừa yêu cầu tìm kiếm theo mã nhân viên, vui lòng nhập mã: ");
		int staffId = console.nextInt();
		console.nextLine();
		boolean invalidId = true;
		for (Staff staff : staffs) {
			if (staff.getId() == staffId) {
				invalidId = false;
				staff.displayInformation();
				break;
			}
		}
		if (invalidId) {
			System.out.println("Mã nhân viên không hợp lệ! Không thể tìm thấy nhân viên!");
			System.out.println();
		}
	}

	// Phương thức tìm kiếm nhân viên bằng tên
	public static void searchStaffByName(Scanner console) {
		System.out.print("Bạn vừa yêu cầu tìm kiếm theo tên nhân viên, vui lòng nhập tên: ");
		String staffName = console.nextLine().trim().toLowerCase();
		boolean invalidName = true;
		for (Staff staff : staffs) {
			if (staffName.equals(staff.getName().toLowerCase())) {
				invalidName = false;
				staff.displayInformation();
				break;
			}
		}
		if (invalidName) {
			System.out.println("Tên nhân viên không hợp lệ! Không thể tìm thấy nhân viên!");
			System.out.println();
		}
	}

	// Phương thức hiển thị bảng lương của tất cả nhân viên
	public static void displayStaffSalary() {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu hiển thị bảng lương nhân viên (Chức năng 6).");
		if (staffs.size() == 0) {
			System.out.println("Hiện tại công ty không có nhân viên nào.");
			System.out.println("Không thể hiển thị bảng lương.");
			System.out.println();
			return;
		}

		String format = "|%5s|%20s|%13s|";
		System.out.println("|----------------------------------------|");
		System.out.println(String.format("|%5s|%20s|%13s|", "ID", "TÊN NHÂN VIÊN", "LƯƠNG"));
		System.out.println("|----------------------------------------|");
		for (Staff staff : staffs) {
			System.out.printf(format, staff.getId(), staff.getName(), staff.calculateSalary() + " VNĐ");
			System.out.println();
		}
		System.out.println("|_____|____________________|_____________|");
		System.out.println();
	}

	// Phương thức sắp xếp nhân viên tăng / giảm dần theo lương và hiển thị danh
	// sách
	public static void displayStaffSortBySalary(Scanner console) {
		System.out.println("THÔNG BÁO: Bạn vừa yêu cầu hiển thị nhân viên đã sắp xếp (Chức năng 7).");
		if (staffs.size() == 0) {
			System.out.println("Hiện tại công ty không có nhân viên nào.");
			System.out.println("Không thể sắp xếp và hiển thị nhân viên.");
			System.out.println();
			return;
		}
		System.out.println("Phím 1. Sắp xếp tăng dần.");
		System.out.println("Phím 2. Sắp xếp giảm dần.");
		System.out.println("Phím 0. Thoát chức năng.");
		System.out.print("-> Chọn chức năng của bạn: ");
		String control = console.nextLine().trim();
		System.out.println();
		// Định nghĩa Comparator tăng / giảm dần theo lương phục vụ cho phương thức sắp
		// xếp
		Comparator<Staff> salaryAscending = (o1, o2) -> o1.calculateSalary() - o2.calculateSalary();
		Comparator<Staff> salaryDescending = (o1, o2) -> o2.calculateSalary() - o1.calculateSalary();
		switch (control) {
		case "0":
			return;
		case "1":
			staffs.sort(salaryAscending); //Phương thức hiển thị bảng lương tăng dần
			break;
		case "2":
			staffs.sort(salaryDescending); //Phương thức hiển thị bảng lương giảm dần
			break;
		default:
			displayError(control); //Phương thức báo lỗi khinhaapj thông tin không hợp lệ
			return;
		}
		displayStaffSalary();
	}

	// Phương thức hiển thị thông báo người dùng nhập yêu cầu không hợp lệ
	public static void displayError(String control) {
		System.out.println("Bạn vừa nhập [" + control + "]. Yêu cầu không hợp lệ!");
		System.out.println("Thực hiện chức năng thất bại! Chuyển về Menu tổng.");
		System.out.println();
	}
}