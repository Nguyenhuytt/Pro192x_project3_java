/*
Lớp Department gồm các thông tin của một bộ phận:
mã bộ phận, tên bộ phận, số nhân viên trong 1 bộ phận.
*/
public class Department {
    private int departmentId;
    private String departmentName;
    private int staffCount;
    //contructor chứa các yếu tố của một bộ phận
    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.staffCount =staffCount ;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staff) {
        this.staffCount = staff;
    }

    public void plusStaffCount() {
        this.staffCount++;
		
    }

    public void subtractStaffCount() {
        this.staffCount--;
    }
    //Phương thức hiển thị bộ phận
    @Override
    public String toString() {
    	return String.format("|%5s|%20s|%13s|",departmentId,departmentName,staffCount);
        
    }
}