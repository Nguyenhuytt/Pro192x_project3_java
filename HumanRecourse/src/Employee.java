/*
Lớp Employee kế thừa lớp Staff thực thi hàm tính lương của ICalculator,
ngoài ra còn có thêm trường giờ làm thêm - overTimeHours.
*/
public class Employee extends Staff {
    private int overTime;
    
    
	protected Employee(int id, String name, int age, Department department, String startDate,
			int dayOff, int overTime, int factorOfSalary) {
		super(id, name, age, factorOfSalary, startDate, department, dayOff);
		this.overTime = overTime;
	}


	public int getOverTime() {
		return overTime;
	}


	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

    //Phương thức hiển thị thông tin của nhân viên
	@Override
    public void displayInformation() {
    	System.out.println(String.format("|%03d|%20s|%5s|%18s|%17s|%13s|%13s|%13s|%11s|%13s VND|", 
    			super.getId(),super.getName(), super.getAge(), super.getDepartment().getDepartmentName(), "Nhan Vien",
				super.getStartDate(), super.getDayOff(), getOverTime(),super.getFactorOfSalary(),(int)calculateSalary()));
    	
    }
	//Phương thức tính lương cho nhân viên
    @Override
    public int calculateSalary() {
        return super.getFactorOfSalary() * 3_000_000 + getOverTime() * 200_000;
    }
}
