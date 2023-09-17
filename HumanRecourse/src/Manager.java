/*
Lớp Manager kế thừa lớp Staff thực thi hàm tính lương của ICalculator,
ngoài ra còn có thêm trường chức danh - title.
*/
public class Manager extends Staff {
    private String title;

    protected Manager(int id, String name, int age, Department department, String title, String startDate,
			int dayOff, int factorOfSalary) {
		super(id, name, age, factorOfSalary, startDate, department, dayOff);
		this.title = title;
	}
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//Phương thức tính lương trách nhiệm dựa trên chức danh
    public int getTitleSalary() {
        switch (title) {
            case "Business Leader":
                return 8_000_000;
            case "Project Leader":
                return 5_000_000;
            case "Technical Leader":
                return 6_000_000;
        }
        return 0;
    }
    //Phương thức hiển thị thông tin của quản lý
    @Override
    public void displayInformation() {
    	System.out.println(String.format("|%03d|%20s|%5s|%18s|%17s|%13s|%13s|%13s|%11s|%13s VND|", super.getId(),
				super.getName(), super.getAge(), super.getDepartment().getDepartmentName(), getTitle(), super.getStartDate(), super.getDayOff(), "0",
				super.getFactorOfSalary(),(int)calculateSalary()));
        
    }
    //Phương thức tính lương quản lý
    @Override
    public int calculateSalary() {
		return super.getFactorOfSalary() * 5000000 +getTitleSalary();
	}


    }
